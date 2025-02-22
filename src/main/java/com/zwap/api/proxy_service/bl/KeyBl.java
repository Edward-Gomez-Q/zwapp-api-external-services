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
import java.util.Objects;
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
        validateInputParameters(companyId, keyDto.getName(), keyDto.getUrl(), keyDto.getEvents());
        EmpresaModel company = getCompanyOrThrow(companyId);
        validateKeyCreationConstraints(companyId, keyDto.getName(), keyDto.getUrl());
        String generatedKey = generateHmacKey(companyId, keyDto.getName());
        LlaveModel savedKey = saveKey(company.getId(), generatedKey, keyDto.getName(), keyDto.getUrl(), keyDto.getEvents());
        return mapToKeyDto(savedKey);
    }

    public List<KeyDto> getKeysForCompany(String companyId, String token) {
        validateAuthToken(token);
        if (!StringUtils.hasText(companyId)) {
            throw new UnprocessableEntityException("El ID de la compañía no puede ser nulo o vacío");
        }
        PocketBaseResponseDto<List<LlaveModel>> response = llaveRepository.findAllKeysById(companyId)
                .orElseThrow(() -> new GatewayTimeoutException("No se pudieron recuperar las llaves"));
        return response.getItems().stream()
                .map(this::mapToKeyDto)
                .toList();
    }

    private void validateAuthToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnprocessableEntityException("El token no puede ser nulo o vacío");
        }
        String privToken = environment.getProperty("PRIV_KEY");
        token = token.replace("Bearer ", "");
        if (!token.equals(privToken)) {
            throw new UnprocessableEntityException("Token inválido");
        }
    }

    private void validateInputParameters(String companyId, String keyName, String keyUrl, List<String> keyType) {
        if (!StringUtils.hasText(companyId)) {
            throw new UnprocessableEntityException("El ID de la compañía es requerido");
        }
        if (!StringUtils.hasText(keyName)) {
            throw new UnprocessableEntityException("El nombre de la llave es requerido");
        }
        if (keyName.length() > 50) {
            throw new UnprocessableEntityException("El nombre de la llave no puede exceder los 50 caracteres");
        }
        if (StringUtils.hasText(keyUrl) && !(keyType != null && !keyType.isEmpty())) {
            throw new UnprocessableEntityException("Cuando se proporciona una URL, se requiere al menos un tipo de evento");
        }
        if (!StringUtils.hasText(keyUrl) && (keyType != null && !keyType.isEmpty())) {
            throw new UnprocessableEntityException("Cuando se proporciona al menos un tipo de evento, se requiere una URL");
        }
        if (keyUrl != null && !isValidBackendUrl(keyUrl)) {
            throw new UnprocessableEntityException("URL de backend inválida");
        }

    }

    private boolean isValidBackendUrl(String url) {
        try {
            URI uri = new URI(url);
            if(Objects.equals(environment.getProperty("ENVIRONMENT"), "prod")) {
                return "https".equalsIgnoreCase(uri.getScheme()) && uri.getHost() != null && !uri.getHost().isEmpty();
            }
            return "http".equalsIgnoreCase(uri.getScheme()) && uri.getHost() != null && !uri.getHost().isEmpty();
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private EmpresaModel getCompanyOrThrow(String companyId) {
        return empresaRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Compañia no encontrada con el ID: " + companyId));
    }

    private void validateKeyCreationConstraints(String companyId, String keyName, String url) {
        PocketBaseResponseDto<List<LlaveModel>> existingKeys = llaveRepository.findAllKeysById(companyId)
                .orElseThrow(() -> new GatewayTimeoutException("Falló al recuperar las llaves"));

        if (existingKeys.getItems().stream().anyMatch(key -> key.getNombre().equals(keyName))) {
            throw new UnprocessableEntityException("Ya existe una llave con el nombre proporcionado");
        }

        if (existingKeys.getItems().size() >= MAX_KEYS_PER_COMPANY) {
            throw new UnprocessableEntityException("Máximo número de llaves (" + MAX_KEYS_PER_COMPANY + ") alcanzado");
        }
        if (url != null && existingKeys.getItems().stream().anyMatch(key -> key.getUrl().equals(url))) {
            throw new UnprocessableEntityException("Ya existe una llave con la URL proporcionada");
        }
    }

    private String generateHmacKey(String companyId, String keyName) {
        try {
            String masterKey = Optional.ofNullable(environment.getProperty("MASTER_KEY"))
                    .orElseThrow(() -> new ConfigurationException("Falló al recuperar la llave maestra"));

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
            throw new BusinessException("Falla al generar la llave");
        }
    }

    private LlaveModel saveKey(String companyId, String key, String keyName, String url, List<String> tipo) {
        return llaveRepository.save(new LlaveModel(companyId, key, keyName, url, tipo))
                .orElseThrow(() -> new GatewayTimeoutException("Falló al guardar la llave"));
    }

    private KeyDto mapToKeyDto(LlaveModel model) {
        return new KeyDto(model.getNombre(), model.getLlave(), model.getUrl(), model.getEventos(), model.getCreated());
    }
}