package com.zwap.api.proxy_service.api;

import com.zwap.api.proxy_service.api.exception.BaseController;
import com.zwap.api.proxy_service.bl.TransaccionBl;
import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.dto.TransactionQueryParams;
import com.zwap.api.proxy_service.dto.TransactionResponseDto;
import com.zwap.api.proxy_service.model.TransaccionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransaccionApi extends BaseController{
    private final TransaccionBl transaccionBl;
    public TransaccionApi(TransaccionBl transaccionBl) {
        this.transaccionBl = transaccionBl;
    }
    //Obtener todas las transacciones por empresa
    @GetMapping("/external/transaccion")
    public ResponseEntity<PocketBaseResponseDto<List<TransactionResponseDto>>> obtenerTransacciones(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String fields
    ) {
        return handleRequest(() -> transaccionBl.obtenerTransacciones(token, new TransactionQueryParams(page, perPage, sort, "", "", fields, false)));
    }
    //Reenviar las transacciones a las empresas
    @PostMapping("/private/transaccion/realtime")
    public ResponseEntity<String> recibirTransaccion(
            //@RequestHeader("Authorization") String token,
            @RequestBody TransaccionModel transaccionModel) {
        Boolean request = transaccionBl.recibirTransaccion("", transaccionModel);
        return request? ResponseEntity.ok("Transaccion recibida"): ResponseEntity.badRequest().body("Transaccion no recibida");
    }

}
