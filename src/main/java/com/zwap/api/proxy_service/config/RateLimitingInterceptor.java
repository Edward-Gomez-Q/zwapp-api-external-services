package com.zwap.api.proxy_service.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
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

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws IOException {
        String apiKey = request.getHeader("Authorization");
        if (apiKey == null || apiKey.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No se proporcionó un token\"}");
            return false;
        }
        Bucket bucket = cache.computeIfAbsent(apiKey, this::createNewBucket);
        if (bucket.tryConsume(1)) {
            return true;
        }
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Limite de peticiones excedido\", \"Reintentar-luego\": \"" +
                bucket.getAvailableTokens() + "\"}");
        return false;
    }
    private Bucket createNewBucket(String key) {
        return Bucket.builder()
                .addLimit(Bandwidth.builder()
                        .capacity(150)
                        .refillIntervally(150, Duration.ofMinutes(1))
                        .build())
                .build();
    }
}
