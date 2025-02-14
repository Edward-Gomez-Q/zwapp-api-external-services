package com.zwap.api.proxy_service.api;

import com.zwap.api.proxy_service.api.exception.BaseController;
import com.zwap.api.proxy_service.bl.KeyBl;
import com.zwap.api.proxy_service.dto.KeyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/key")
@RestController
public class KeyApi extends BaseController {
    private final KeyBl keyBl;

    public KeyApi(KeyBl keyBl) {
        this.keyBl = keyBl;
    }

    //Post para crear un nuevo key
    @PostMapping("/{id}")
    public ResponseEntity<KeyDto> createKey(
            @PathVariable String id,
            @RequestParam String nombre,
            @RequestHeader("Authorization") String token
    ) {
        return handleRequest(() -> keyBl.createKey(id, nombre, token));
    }

    //Get para obtener todos los keys de una empresa
    @GetMapping("/{id}")
    public ResponseEntity<List<KeyDto>> getKeysById(@PathVariable String id, @RequestHeader("Authorization") String token) {
        return handleRequest(() -> keyBl.getKeysForCompany(id, token));
    }
}
