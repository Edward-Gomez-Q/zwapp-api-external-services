package com.zwap.api.proxy_service.api;

import com.zwap.api.proxy_service.api.exception.BaseController;
import com.zwap.api.proxy_service.bl.TransaccionBl;
import com.zwap.api.proxy_service.dto.RealtimeTransaccionDto;
import com.zwap.api.proxy_service.dto.RegisterWebHookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("")
public class TransaccionApi extends BaseController{
    private final TransaccionBl transaccionBl;
    public TransaccionApi(TransaccionBl transaccionBl) {
        this.transaccionBl = transaccionBl;
    }

    @PostMapping("/register")
    public SseEmitter suscribirse(@RequestHeader("Authorization") String token, @RequestBody RegisterWebHookDto registerWebHookDto) {
        return transaccionBl.suscribirse(token, registerWebHookDto.getUrl());
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<String> desuscribirse(@RequestHeader("Authorization") String token) {
        transaccionBl.desuscribirse(token);
        return ResponseEntity.ok("Desuscripción exitosa");
    }
}
