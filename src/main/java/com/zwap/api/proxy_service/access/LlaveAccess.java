package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Repository
public class LlaveAccess implements LlaveRepository {
    private final WebClient webClient;
    private static final Logger log = LoggerFactory.getLogger(LlaveAccess.class);

    public LlaveAccess(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<PocketBaseResponseDto<List<LlaveModel>>> findAllKeysById(String id) {
        try {
            Optional<PocketBaseResponseDto<List<LlaveModel>>> response = Optional.ofNullable(webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/collections/llaves_api_gateway/records")
                            .queryParam("filter", "(empresa='{id}')")
                            .build(id))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<PocketBaseResponseDto<List<LlaveModel>>>() {})
                    .block());
            log.info("Llaves encontradas: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error al obtener las llaves: {}", e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Optional<LlaveModel> save(LlaveModel llaveModel) {
        try {
            Optional<LlaveModel> response = Optional.ofNullable(webClient.post()
                    .uri("/api/collections/llaves_api_gateway/records")
                    .bodyValue(llaveModel)
                    .retrieve()
                    .bodyToMono(LlaveModel.class)
                    .block());
            log.info("Llave guardada: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error al guardar la llave: {}", e.getMessage());
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
            log.info("Llave encontrada: {}", response);
            if (response.isPresent() && !response.get().getItems().isEmpty()) {
                return Optional.of(response.get().getItems().getFirst());
            }
            log.error("No se encontró la llave");
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error al obtener la llave: {}", e.getMessage());
            return Optional.empty();
        }
    }

}
