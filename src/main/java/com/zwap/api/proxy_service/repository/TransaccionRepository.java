package com.zwap.api.proxy_service.repository;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.dto.RealtimeTransaccionDto;
import com.zwap.api.proxy_service.model.TransaccionModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransaccionRepository {
    Optional<PocketBaseResponseDto<List<TransaccionModel>>> findAllTransacciones();
}
