package com.zwap.api.proxy_service.bl;

import com.zwap.api.proxy_service.api.exception.common.BusinessException;
import com.zwap.api.proxy_service.api.exception.common.GatewayTimeoutException;
import com.zwap.api.proxy_service.api.exception.common.NotFoundException;
import com.zwap.api.proxy_service.api.exception.common.UnprocessableEntityException;
import com.zwap.api.proxy_service.dto.KeyDto;
import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.model.EmpresaModel;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.repository.EmpresaRepository;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.ConfigurationException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class KeyBl {
    private static final Logger logger = LoggerFactory.getLogger(KeyBl.class);
    private static final String HMAC_ALGORITHM = "HmacSHA512";
    private static final int MAX_KEYS_PER_COMPANY = 5;

    private final EmpresaRepository empresaRepository;
    private final Environment environment;
    private final LlaveRepository llaveRepository;

    public KeyBl(EmpresaRepository empresaRepository, Environment environment, LlaveRepository llaveRepository) {
        this.empresaRepository = empresaRepository;
        this.environment = environment;
        this.llaveRepository = llaveRepository;
    }

    public KeyDto createKey(String companyId, KeyDto keyDto, String token) {
        validateAuthToken(token);
        validateInputParameters(companyId, keyDto.getName(), keyDto.getUrl(), keyDto.getType());
        EmpresaModel company = getCompanyOrThrow(companyId);
        validateKeyCreationConstraints(companyId, keyDto.getName());
        String generatedKey = generateHmacKey(companyId, keyDto.getName());
        LlaveModel savedKey = saveKey(company.getId(), generatedKey, keyDto.getName(), keyDto.getUrl(), keyDto.getType());
        return mapToKeyDto(savedKey);
    }

    public List<KeyDto> getKeysForCompany(String companyId, String token) {
        validateAuthToken(token);
        validateInputParameters(companyId, "", "", "");
        getCompanyOrThrow(companyId);
        PocketBaseResponseDto<List<LlaveModel>> response = llaveRepository.findAllKeysById(companyId)
                .orElseThrow(() -> new GatewayTimeoutException("Failed to retrieve keys"));
        return response.getItems().stream()
                .map(this::mapToKeyDto)
                .toList();
    }

    private void validateAuthToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnprocessableEntityException("Token cannot be null or empty");
        }
        String privToken = environment.getProperty("PRIV_KEY");
        token = token.replace("Bearer ", "");
        if (!token.equals(privToken)) {
            throw new UnprocessableEntityException("Invalid token");
        }
    }

    private void validateInputParameters(String companyId, String keyName, String keyUrl, String keyType) {
        if (!StringUtils.hasText(companyId)) {
            throw new UnprocessableEntityException("Company ID cannot be empty");
        }
        if (!StringUtils.hasText(keyName)) {
            throw new UnprocessableEntityException("Key name is required");
        }
        if (keyName.length() > 50) {
            throw new UnprocessableEntityException("Key name cannot exceed 50 characters");
        }
        if (StringUtils.hasText(keyUrl) && !isValidBackendUrl(keyUrl)) {
            throw new UnprocessableEntityException("Invalid URL format");
        }
        if (StringUtils.hasText(keyType) && keyType.length() > 50) {
            throw new UnprocessableEntityException("Key type cannot exceed 50 characters");
        }
        if ((StringUtils.hasText(keyUrl) && !StringUtils.hasText(keyType)) ||
                (!StringUtils.hasText(keyUrl) && StringUtils.hasText(keyType))) {
            throw new UnprocessableEntityException("Key URL and Key Type must be provided together");
        }
    }
    private boolean isValidBackendUrl(String url) {
        try {
            URI uri = new URI(url);
            return "http".equalsIgnoreCase(uri.getScheme()) && uri.getHost() != null && !uri.getHost().isEmpty();
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private EmpresaModel getCompanyOrThrow(String companyId) {
        return empresaRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found with ID: " + companyId));
    }

    private void validateKeyCreationConstraints(String companyId, String keyName) {
        PocketBaseResponseDto<List<LlaveModel>> existingKeys = llaveRepository.findAllKeysById(companyId)
                .orElseThrow(() -> new GatewayTimeoutException("Failed to retrieve existing keys"));

        if (existingKeys.getItems().stream().anyMatch(key -> key.getNombre().equals(keyName))) {
            throw new UnprocessableEntityException("A key with this name already exists");
        }

        if (existingKeys.getItems().size() >= MAX_KEYS_PER_COMPANY) {
            throw new UnprocessableEntityException("Maximum number of keys (" + MAX_KEYS_PER_COMPANY + ") reached");
        }
    }

    private String generateHmacKey(String companyId, String keyName) {
        try {
            String masterKey = Optional.ofNullable(environment.getProperty("MASTER_KEY"))
                    .orElseThrow(() -> new ConfigurationException("MASTER_KEY not configured"));

            String timestamp = Instant.now()
                    .atOffset(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ISO_INSTANT);

            String dataToHash = String.join("", companyId, timestamp, keyName);

            Mac hmac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(masterKey.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
            hmac.init(secretKey);

            byte[] hmacData = hmac.doFinal(dataToHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmacData);

        } catch (Exception e) {
            logger.error("Failed to generate HMAC key", e);
            throw new BusinessException("Failed to generate key");
        }
    }

    private LlaveModel saveKey(String companyId, String key, String keyName, String url, String tipo) {
        return llaveRepository.save(new LlaveModel(companyId, key, keyName, url, tipo))
                .orElseThrow(() -> new GatewayTimeoutException("Failed to save key"));
    }

    private KeyDto mapToKeyDto(LlaveModel model) {
        return new KeyDto(model.getNombre(), model.getLlave(), model.getUrl(), model.getTipo(), model.getCreated());
    }
}