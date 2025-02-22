package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Repository
public class LlaveAccess implements LlaveRepository {
    private final WebClient webClient;

    public LlaveAccess(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<PocketBaseResponseDto<List<LlaveModel>>> findAllKeysById(String id) {
        try {
            return Optional.ofNullable(webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/collections/llaves_api_gateway/records")
                            .queryParam("filter", "(empresa='{id}')")
                            .build(id))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<PocketBaseResponseDto<List<LlaveModel>>>() {})
                    .block());
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<LlaveModel> save(LlaveModel llaveModel) {
        try {
            return Optional.ofNullable(webClient.post()
                    .uri("/api/collections/llaves_api_gateway/records")
                    .bodyValue(llaveModel)
                    .retrieve()
                    .bodyToMono(LlaveModel.class)
                    .block());
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la llave: Por favor revise la documentación de la API");
        }
    }

    @Override
    public Optional<LlaveModel> findByLlave(String id) {
        try {
            Optional<PocketBaseResponseDto<List<LlaveModel>>> response = Optional.ofNullable(webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/collections/llaves_api_gateway/records")
                            .queryParam("filter", "(llave='{id}')")
                            .build(id))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<PocketBaseResponseDto<List<LlaveModel>>>() {})
                    .block());
            if (response.isPresent() && !response.get().getItems().isEmpty()) {
                return Optional.of(response.get().getItems().getFirst());
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
