package com.zwap.api.proxy_service.api;

import com.zwap.api.proxy_service.api.exception.BaseController;
import com.zwap.api.proxy_service.bl.EnlaceBl;
import com.zwap.api.proxy_service.dto.EnlaceDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoDto;
import com.zwap.api.proxy_service.dto.EnlacePersonalizadoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/external/pago/enlace")
@RestController
public class EnlaceApi extends BaseController {
    private final EnlaceBl enlaceBl;
    public EnlaceApi(EnlaceBl enlaceBl) {
        this.enlaceBl = enlaceBl;
    }

    @GetMapping("/permanente")
    public ResponseEntity<EnlaceDto> getEnlacePermanente(@RequestHeader("Authorization") String token) {
        return handleRequest(() -> enlaceBl.getEnlacePermanente(token));
    }

    @PostMapping("/personalizado")
    public ResponseEntity<EnlacePersonalizadoResponseDto> getEnlacePersonalizado(
            @RequestHeader("Authorization")String token,
            @RequestBody EnlacePersonalizadoDto enlacePersonalizadoDto) {
        return handleRequest(() -> enlaceBl.getEnlacePersonalizado(token, enlacePersonalizadoDto));
    }
}
