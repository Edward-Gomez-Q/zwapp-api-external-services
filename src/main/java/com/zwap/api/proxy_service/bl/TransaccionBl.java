package com.zwap.api.proxy_service.bl;

import com.zwap.api.proxy_service.api.exception.common.UnprocessableEntityException;
import com.zwap.api.proxy_service.dto.RealtimeTransaccionDto;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.model.SuscriptorModel;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransaccionBl {
    private final Map<String, List<SuscriptorModel>> suscriptores = new ConcurrentHashMap<>();
    private final LlaveRepository llaveRepository;

    public TransaccionBl(LlaveRepository llaveRepository) {
        this.llaveRepository = llaveRepository;
    }

    public SseEmitter suscribirse(String token, String webHookUrl) {
        token = validateAuthToken(token);
        LlaveModel llave = llaveRepository.findByLlave(token).orElse(null);
        validateLlave(llave);
        String idEmpresa = llave.getEmpresa();
        List<SuscriptorModel> lista = suscriptores.get(idEmpresa);
        if (lista != null) {
            throw new UnprocessableEntityException("Ya existe un suscriptor con el token: " + token);
        }
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        SuscriptorModel suscriptor = new SuscriptorModel(token, idEmpresa, emitter);
        suscriptores.computeIfAbsent(idEmpresa, k -> new ArrayList<>()).add(suscriptor);
        try {
            emitter.send(SseEmitter.event()
                    .name("INIT")
                    .data("Connected successfully"));
        } catch (IOException e) {
            removerSuscriptor(token, idEmpresa);
            throw new RuntimeException("Error establishing connection", e);
        }
        String finalToken = token;
        emitter.onCompletion(() -> {
            System.out.println("Connection completed for token: " + finalToken);
            removerSuscriptor(finalToken, idEmpresa);
        });
        emitter.onTimeout(() -> {
            System.out.println("Connection timed out for token: " + finalToken);
            removerSuscriptor(finalToken, idEmpresa);
        });
        emitter.onError(ex -> {
            System.out.println("Error in connection for token: " + finalToken + " - " + ex.getMessage());
            removerSuscriptor(finalToken, idEmpresa);
        });
        System.out.println("Suscripción exitosa: " + idEmpresa);
        return emitter;
    }
    private String validateAuthToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnprocessableEntityException("Token cannot be null or empty");
        }
        token = token.replace("Bearer ", "");
        return token;
    }
    private void validateLlave(LlaveModel llave) {
        if (llave == null) {
            throw new UnprocessableEntityException("Invalid token");
        }
    }

    public void desuscribirse(String token) {
        token = validateAuthToken(token);
        LlaveModel llave = llaveRepository.findByLlave(token).orElse(null);
        validateLlave(llave);
        String idEmpresa = llave.getEmpresa();

        List<SuscriptorModel> lista = suscriptores.get(idEmpresa);
        if (lista != null) {
            String finalToken = token;
            String finalToken1 = token;
            lista.stream()
                    .filter(s -> s.getToken().equals(finalToken))
                    .findFirst()
                    .ifPresent(suscriptor -> {
                        try {
                            // Send closing event before completing
                            suscriptor.getEmitter().send(SseEmitter.event()
                                    .name("CLOSE")
                                    .data("Connection closed"));
                            suscriptor.getEmitter().complete();
                        } catch (IOException e) {
                            System.out.println("Error sending close event: " + e.getMessage());
                        } finally {
                            removerSuscriptor(finalToken1, idEmpresa);
                        }
                    });
        } else {
            throw new UnprocessableEntityException("No existe un suscriptor con el token: " + token);
        }
    }

    private void removerSuscriptor(String token, String idEmpresa) {
        List<SuscriptorModel> lista = suscriptores.get(idEmpresa);
        if (lista != null) {
            lista.removeIf(s -> {
                if (s.getToken().equals(token)) {
                    try {
                        s.getEmitter().complete();
                    } catch (Exception e) {
                        System.out.println("Error completing emitter: " + e.getMessage());
                    }
                    return true;
                }
                return false;
            });

            if (lista.isEmpty()) {
                suscriptores.remove(idEmpresa);
            }
            System.out.println("Suscriptor removido: " + token);
        }
    }

    public void sendEvent(RealtimeTransaccionDto transaccion) {
        List<SuscriptorModel> lista = suscriptores.get(transaccion.getRecord().getEmpresa());
        if (lista != null) {
            List<SuscriptorModel> toRemove = new ArrayList<>();

            lista.forEach(s -> {
                try {
                    s.getEmitter().send(transaccion);
                } catch (Exception e) {
                    System.out.println("Error sending event to " + s.getToken() + ": " + e.getMessage());
                    toRemove.add(s);
                }
            });
            toRemove.forEach(s -> removerSuscriptor(s.getToken(), s.getIdEmpresa()));
        }
    }
}
