package com.zwap.api.proxy_service.repository;

import com.zwap.api.proxy_service.dto.EnlacePersonalizadoDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoResponseDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkPagoRepository {
    Optional<EnlacePersonalizadoResponseDto> createEnlacePersonalizado(EnlacePersonalizadoDto enlacePersonalizadoDto);
}
