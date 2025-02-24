package com.zwap.api.proxy_service.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(RateLimitingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws IOException {
        try {
            String apiKey = request.getHeader("Authorization");
            if (apiKey == null || apiKey.isEmpty()) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"No se proporcionó un token\"}");
                log.error("No se proporcionó un token de autorización, falla en RateLimitingInterceptor");
                return false;
            }
            Bucket bucket = cache.computeIfAbsent(apiKey, this::createNewBucket);
            if (bucket.tryConsume(1)) {
                log.info("Petición permitida, se consumió un token");
                return true;
            }
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Limite de peticiones excedido\", \"Reintentar-luego\": \"" +
                    bucket.getAvailableTokens() + "\"}");
            log.error("Limite de peticiones excedido, falla en RateLimitingInterceptor");
            return false;
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Error interno del servidor\"}");
            log.error("Error interno del servidor, falla en RateLimitingInterceptor", e);
            return false;
        }
    }
    private Bucket createNewBucket(String key) {
        try{
            return Bucket.builder()
                    .addLimit(Bandwidth.builder()
                            .capacity(150)
                            .refillIntervally(150, Duration.ofMinutes(1))
                            .build())
                    .build();
        } catch (Exception e) {
            log.error("Error al crear un nuevo Bucket", e);
            throw new RuntimeException("Error al crear un nuevo Bucket");
        }
    }
}
