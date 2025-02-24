package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.dto.TransactionQueryParams;
import com.zwap.api.proxy_service.dto.TransactionResponseDto;
import com.zwap.api.proxy_service.model.TransaccionModel;
import com.zwap.api.proxy_service.repository.TransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Repository
public class TransaccionAccess implements TransaccionRepository {
    private final WebClient webClient;
    private static final Logger log = LoggerFactory.getLogger(TransaccionAccess.class);
    public TransaccionAccess(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<PocketBaseResponseDto<List<TransactionResponseDto>>> findAllTransacciones(String token, TransactionQueryParams params) {
        try {
            Optional<PocketBaseResponseDto<List<TransactionResponseDto>>> response = Optional.ofNullable(webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/collections/transacciones/records")
                            .queryParamIfPresent("page", Optional.ofNullable(params.getPage()))
                            .queryParamIfPresent("perPage", Optional.ofNullable(params.getPerPage()))
                            .queryParamIfPresent("sort", Optional.ofNullable(params.getSort()))
                            .queryParamIfPresent("filter", Optional.of("(empresa='{token}')"))
                            .queryParamIfPresent("fields", Optional.ofNullable(params.getFields()))
                            .build(token))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<PocketBaseResponseDto<List<TransactionResponseDto>>>() {})
                    .block());
            log.info("Transacciones encontradas: {}", response);
            return response;
        } catch (WebClientResponseException e) {
            log.error("Error al obtener las transacciones: {}", e.getMessage());
            throw new RuntimeException("Error al obtener las transaccione");
        }
    }
}
