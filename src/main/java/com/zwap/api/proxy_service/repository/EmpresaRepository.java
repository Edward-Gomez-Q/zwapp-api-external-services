package com.zwap.api.proxy_service.repository;

import com.zwap.api.proxy_service.model.EmpresaModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository {
    Optional<EmpresaModel> findById(String id);
}
