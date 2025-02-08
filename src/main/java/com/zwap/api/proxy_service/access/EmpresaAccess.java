package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.model.EmpresaModel;
import com.zwap.api.proxy_service.repository.EmpresaRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class EmpresaAccess implements EmpresaRepository {
    private final WebClient webClient;

    public EmpresaAccess(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<EmpresaModel> findById(String id) {
        try{
            EmpresaModel response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/collections/empresas/records/{id}")
                            .build(id))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<EmpresaModel>() {})
                    .block();
            return response != null ? Optional.of(response) : Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la empresa");
        }
    }
}
