package com.zwap.api.proxy_service.bl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwap.api.proxy_service.dto.ClientIdRequest;
import com.zwap.api.proxy_service.dto.RealtimeTransaccionDto;
import com.zwap.api.proxy_service.dto.ClientIdDto;
import com.zwap.api.proxy_service.model.TransaccionModel;
import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PocketRealtimeBl {
    private final WebClient webClient;
    private final TransaccionBl transaccionBl;
    public PocketRealtimeBl(WebClient webClient, TransaccionBl transaccionBl) {
        this.webClient = webClient;
        this.transaccionBl = transaccionBl;
    }
    @PostConstruct
    public void startRealtimeSubscription() {
        subscribeToPocketBaseRealtime();
    }
    public void subscribeToPocketBaseRealtime() {
        webClient.get()
                .uri("/api/realtime")
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {})
                .doOnError(error -> {
                    System.err.println("Error en la conexión SSE: " + error.getMessage());
                    reconnect();
                })
                .flatMap(this::handleEvent)
                .subscribe();
    }

    private Mono<Void> handleEvent(Map<String, Object> event) {
        if (event.containsKey("clientId")) {
            ClientIdDto clientId = new ClientIdDto((String) event.get("clientId"));
            System.out.println("clientId: " + clientId);
            return handleClientId(clientId);
        } else if (event.containsKey("action") && event.containsKey("record")) {
            RealtimeTransaccionDto transaccion = new RealtimeTransaccionDto();
            transaccion.setAction((String) event.get("action"));
            if(!transaccion.getAction().equals("update")) {
                return Mono.empty();
            }
            transaccion.setRecord(convertToTransaccionModel(event.get("record")));
            if(transaccion.getRecord().getVoucherUrl() == null || transaccion.getRecord().getCharge() == null) {
                return Mono.empty();
            }
            System.out.println ("Transaccion: " + transaccion);
            transaccionBl.sendEvent(transaccion);
            return Mono.empty();
        } else {
            System.out.println("Evento no manejado: " + event);
            return Mono.empty();
        }
    }

    private Mono<Void> handleClientId(ClientIdDto clientId) {
        return webClient.post()
                .uri("/api/realtime")
                .bodyValue(new ClientIdRequest(clientId.getClientId(), List.of("transacciones")))
                .exchangeToMono(response -> {
                    System.out.println("HTTP Status: " + response.statusCode());
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(ClientIdRequest.class)
                                .doOnNext(responseS -> System.out.println("Response body: " + responseS))
                                .then();
                    } else {
                        return response.createException()
                                .flatMap(error -> {
                                    System.err.println("Error response: " + error.getMessage());
                                    return Mono.empty();
                                });
                    }
                });
    }

    private TransaccionModel convertToTransaccionModel(Object record) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (record instanceof Map<?, ?> recordMap) {
            ((Map<String, Object>) recordMap).remove("stripeResponse");
        }
        return objectMapper.convertValue(record, TransaccionModel.class);
    }

    private void reconnect() {
        System.out.println("Intentando reconectar en 1 segundos...");
        Mono.delay(Duration.ofSeconds(1))
                .doOnTerminate(this::subscribeToPocketBaseRealtime)
                .subscribe();
    }
}
