package com.zwap.api.proxy_service.access;

import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.model.TransaccionModel;
import com.zwap.api.proxy_service.repository.TransaccionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Repository
public class TransaccionAccess implements TransaccionRepository {
    private final WebClient webClient;

    public TransaccionAccess(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<PocketBaseResponseDto<List<TransaccionModel>>> findAllTransacciones() {
        return Optional.empty();
    }

}
