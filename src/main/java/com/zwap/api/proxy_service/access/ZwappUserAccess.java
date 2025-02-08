package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.model.ZwappUserModel;
import com.zwap.api.proxy_service.repository.ZwappUserRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Repository
public class ZwappUserAccess implements ZwappUserRepository {
    private final WebClient webClient;
    public ZwappUserAccess(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public Optional<ZwappUserModel> getZwappuserByIdEmpresa(String idEmpresa) {
        try {
            PocketBaseResponseDto<List<ZwappUserModel>> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/collections/zwapp_users/records")
                            .queryParam("filter", String.format("empresa='%s'", idEmpresa))
                            .queryParam("sort", "created")
                            .queryParam("perPage", "1")
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<PocketBaseResponseDto<List<ZwappUserModel>>>() {})
                    .block();
            return response != null && !response.getItems().isEmpty()
                    ? Optional.of(response.getItems().getFirst())
                    : Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario más antiguo");
        }
    }
}
