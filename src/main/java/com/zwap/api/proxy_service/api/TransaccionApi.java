package com.zwap.api.proxy_service.api;

import com.zwap.api.proxy_service.api.exception.BaseController;
import com.zwap.api.proxy_service.bl.TransaccionBl;
import com.zwap.api.proxy_service.model.TransaccionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaccion")
public class TransaccionApi extends BaseController{
    private final TransaccionBl transaccionBl;
    public TransaccionApi(TransaccionBl transaccionBl) {
        this.transaccionBl = transaccionBl;
    }

    @PostMapping("/realtime")
    public ResponseEntity<String> recibirTransaccion(
            //@RequestHeader("Authorization") String token,
            @RequestBody TransaccionModel transaccionModel) {
        Boolean request = transaccionBl.recibirTransaccion("", transaccionModel);
        return request? ResponseEntity.ok("Transaccion recibida"): ResponseEntity.badRequest().body("Transaccion no recibida");
    }
}
