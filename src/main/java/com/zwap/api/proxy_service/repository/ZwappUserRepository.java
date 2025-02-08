package com.zwap.api.proxy_service.repository;

import com.zwap.api.proxy_service.model.ZwappUserModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZwappUserRepository {
    Optional<ZwappUserModel> getZwappuserByIdEmpresa(String idEmpresa);
}
