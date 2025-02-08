package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.EnlacePersonalizadoDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoResponseDto;
import com.zwap.api.proxy_service.repository.LinkPagoRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class LinkPagoAccess implements LinkPagoRepository {
    private final WebClient webClient;
    public LinkPagoAccess(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public Optional<EnlacePersonalizadoResponseDto> createEnlacePersonalizado(EnlacePersonalizadoDto enlacePersonalizadoDto) {
        try {
            Optional<EnlacePersonalizadoResponseDto> response = Optional.ofNullable(webClient.post()
                    .uri("/api/custom/external/create-checkout")
                    .bodyValue(enlacePersonalizadoDto)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<EnlacePersonalizadoResponseDto>() {})
                    .block());
            System.out.println("response: " + response);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el enlace personalizado");
        }
    }
}
