package com.zwap.api.proxy_service.bl;

import com.zwap.api.proxy_service.api.exception.common.UnprocessableEntityException;
import com.zwap.api.proxy_service.dto.PocketBaseResponseDto;
import com.zwap.api.proxy_service.dto.TransactionQueryParams;
import com.zwap.api.proxy_service.dto.TransactionResponseDto;
import com.zwap.api.proxy_service.model.LlaveModel;
import com.zwap.api.proxy_service.model.TransaccionModel;
import com.zwap.api.proxy_service.repository.LlaveRepository;
import com.zwap.api.proxy_service.repository.TransaccionRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TransaccionBl {
    private final LlaveRepository llaveRepository;
    private final Environment environment;
    private final WebClient webClient;
    private final TransaccionRepository transaccionRepository;

    private static final Logger log = Logger.getLogger(TransaccionBl.class.getName());

    public TransaccionBl( Environment environment, WebClient webClient, LlaveRepository llaveRepository, TransaccionRepository transaccionRepository) {
        this.environment = environment;
        this.webClient = webClient;
        this.llaveRepository = llaveRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public Boolean recibirTransaccion(String token, TransaccionModel transaccionModel) {
        try {
            transaccionModel.setStripeResponse(null);
            //validateTokenWithPriToken(token);
            return sendEventViaHTTP(transaccionModel, "Transacciones");
        } catch (Exception e) {
            throw new UnprocessableEntityException("Error al enviar la transacción");
        }
    }
    @NotNull
    private Boolean sendEventViaHTTP(TransaccionModel transaccion, String collection) {
        try {
            String idEmpresa = transaccion.getEmpresa();
            PocketBaseResponseDto<List<LlaveModel>> llaves = llaveRepository.findAllKeysById(idEmpresa)
                    .orElseThrow(() -> new UnprocessableEntityException("No se pudieron recuperar las llaves"));
            TransactionResponseDto request = new TransactionResponseDto(transaccion);
            llaves.getItems().stream()
                    .filter(llave -> llave.getEventos().contains(collection))
                    .forEach(llave -> {
                        try {
                            if (llave.getUrl() == null || llave.getUrl().isEmpty()) {
                                throw new UnprocessableEntityException("La llave no tiene una URL válida");
                            }
                            Integer statusCode = webClient.post()
                                    .uri(llave.getUrl())
                                    .bodyValue(request)
                                    .exchangeToMono(response -> Mono.just(response.statusCode().value()))
                                    .block();
                            statusCode = statusCode == null ? 0 : statusCode;
                            switch (statusCode / 100) {
                                case 2:
                                    log.info(String.format("Solicitud exitosa a %s: %d - Transacción procesada correctamente",
                                            llave.getUrl(), statusCode));
                                    break;
                                case 4:
                                    log.warning(String.format("Error de cliente al enviar la transacción a %s: %d - Revisar datos de la solicitud",
                                            llave.getUrl(), statusCode));
                                    break;
                                case 5:
                                    log.severe(String.format("Error del servidor al enviar la transacción a %s: %d - Servicio destino no disponible",
                                            llave.getUrl(), statusCode));
                                    break;
                                default:
                                    log.warning(String.format("Código de estado inesperado al enviar la transacción a %s: %d",
                                            llave.getUrl(), statusCode));
                            }

                        } catch (Exception e) {
                            log.severe(String.format("Error al enviar la transacción a la llave %s: %s",
                                    llave.getId(), e.getMessage()));
                        }
                    });
            return true;
        } catch (Exception e) {
            log.severe(String.format("Error al enviar la transacción: %s", e.getMessage()));
            return false;
        }
    }

    private void validateTokenWithPriToken(String token) {
        String privToken = environment.getProperty("PRIV_KEY");
        if (!token.equals(privToken)) {
            throw new UnprocessableEntityException("Invalid token");
        }
    }

    public PocketBaseResponseDto<List<TransactionResponseDto>> obtenerTransacciones(String tokens, TransactionQueryParams params) {
        try {
            String tokenValidated = validarInputParameters(tokens);
            LlaveModel llaveModel = llaveRepository.findByLlave(tokenValidated)
                    .orElseThrow(() -> new UnprocessableEntityException("El token no es válido"));
            return transaccionRepository.findAllTransacciones(llaveModel.getEmpresa(), params)
                    .orElseThrow(() -> new UnprocessableEntityException("No se pudieron recuperar las transacciones"));
        } catch (Exception e) {
            throw new UnprocessableEntityException("Error al obtener las transacciones");
        }
    }
    private String validarInputParameters(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnprocessableEntityException("El token no puede ser nulo o vacío");
        }
        return token.replace("Bearer ", "");
    }
}
