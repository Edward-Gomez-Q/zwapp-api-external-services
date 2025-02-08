package com.zwap.api.proxy_service.repository;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.model.LlaveModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LlaveRepository {
    Optional<PocketBaseResponseDto<List<LlaveModel>>> findAllKeysById(String id);
    Optional<LlaveModel> save(LlaveModel llaveModel);
    Optional<LlaveModel> findByLlave(String token);
}
