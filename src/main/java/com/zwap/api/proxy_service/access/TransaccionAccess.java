package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.dto.TransactionQueryParams;
import com.zwap.api.proxy_service.dto.TransactionResponseDto;
import com.zwap.api.proxy_service.model.TransaccionModel;
import com.zwap.api.proxy_service.repository.TransaccionRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Repository
public class TransaccionAccess implements TransaccionRepository {
    private final WebClient webClient;

    public TransaccionAccess(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<PocketBaseResponseDto<List<TransactionResponseDto>>> findAllTransacciones(String token, TransactionQueryParams params) {
        try {
            return Optional.ofNullable(webClient.get()
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
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error al obtener las transacciones: " + e.getMessage());
        }
    }
}
