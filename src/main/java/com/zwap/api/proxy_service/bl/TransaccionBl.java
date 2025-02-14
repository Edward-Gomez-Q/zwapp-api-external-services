package com.zwap.api.proxy_service.bl;

import com.zwap.api.proxy_service.dto.RealtimeTransaccionDto;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.model.SuscriptorModel;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
        LlaveModel llave = llaveRepository.findByLlave(token).orElse(null);
        if (llave == null) {
            return null;
        }
        String idEmpresa = llave.getEmpresa();
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        SuscriptorModel suscriptor = new SuscriptorModel(token, idEmpresa, emitter);
        suscriptores.computeIfAbsent(idEmpresa, k -> new ArrayList<>()).add(suscriptor);
        emitter.onCompletion(() -> removerSuscriptor(token, idEmpresa));
        emitter.onTimeout(() -> removerSuscriptor(token, idEmpresa));
        return emitter;
    }

    public void desuscribirse(String token) {
        LlaveModel llave = llaveRepository.findByLlave(token).orElse(null);
        if (llave == null) {
            return;
        }
        removerSuscriptor(token, llave.getEmpresa());
    }

    private void removerSuscriptor(String token, String idEmpresa) {
        List<SuscriptorModel> lista = suscriptores.get(idEmpresa);
        if (lista != null) {
            lista.removeIf(s -> s.getToken().equals(token));
            if (lista.isEmpty()) {
                suscriptores.remove(idEmpresa);
            }
        }
    }

    public void sendEvent(RealtimeTransaccionDto transaccion) {
        List<SuscriptorModel> lista = suscriptores.get(transaccion.getRecord().getEmpresa());
        if (lista != null) {
            lista.forEach(s -> {
                try {
                    s.getEmitter().send(transaccion);
                } catch (Exception e) {
                    removerSuscriptor(s.getToken(), s.getIdEmpresa());
                }
            });
        }
    }
}
