package com.zwap.api.proxy_service.bl;

import com.zwap.api.proxy_service.api.exception.common.UnprocessableEntityException;
import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.dto.RealtimeTransaccionDto;
import com.zwap.api.proxy_service.model.EmpresaModel;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.model.SuscriptorModel;
import com.zwap.api.proxy_service.model.TransaccionModel;
import com.zwap.api.proxy_service.repository.EmpresaRepository;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransaccionBl {
    private final LlaveRepository llaveRepository;
    private final Environment environment;
    private final WebClient webClient;

    public TransaccionBl(LlaveRepository llaveRepository, Environment environment, WebClient webClient) {
        this.llaveRepository = llaveRepository;
        this.environment = environment;
        this.webClient = webClient;
    }

    public Boolean recibirTransaccion(String token, TransaccionModel transaccionModel) {
        try {
            transaccionModel.setStripeResponse(null);
            //validateTokenWithPriToken(token);
            System.out.println("Transacción recibida con el siguiente contenido: " + transaccionModel);
            return sendEventViaHTTP(transaccionModel, "Transacciones");
        } catch (Exception e) {
            throw new UnprocessableEntityException("Error al enviar la transacción");
        }
    }
    @NotNull
    private Boolean sendEventViaHTTP(TransaccionModel transaccion, String collection) {
        try{
            String idEmpresa = transaccion.getEmpresa();
            PocketBaseResponseDto<List<LlaveModel>> llaves = llaveRepository.findAllKeysById(idEmpresa).orElseThrow(() -> new UnprocessableEntityException("No se pudieron recuperar las llaves"));
            llaves.getItems().stream()
                    .filter(llave -> llave.getTipo().equals(collection))
                    .forEach(llave -> {
                        try {
                            if (llave.getUrl() == null || llave.getUrl().isEmpty()) {
                                throw new UnprocessableEntityException("La llave no tiene una URL válida");
                            }
                            webClient.post()
                                    .uri(llave.getUrl())
                                    .bodyValue(transaccion)
                                    .retrieve()
                                    .bodyToMono(TransaccionModel.class)
                                    .block();
                        } catch (Exception e) {
                            throw new UnprocessableEntityException("Error al enviar la transacción a la llave: " + llave.getId());
                        }
                    });
            return true;
        } catch (Exception e) {
            throw new UnprocessableEntityException("Error al enviar la transacción");
        }
    }
    private void validateTokenWithPriToken(String token) {
        String privToken = environment.getProperty("PRIV_KEY");
        if (!token.equals(privToken)) {
            throw new UnprocessableEntityException("Invalid token");
        }
    }
}
