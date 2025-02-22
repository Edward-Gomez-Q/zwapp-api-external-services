package com.zwap.api.proxy_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Configuration
public class WebClientConfig {

    private final Environment env;
    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);
    private LocalDateTime tokenExpiration;
    private String authToken;
    public WebClientConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public WebClient webClient() {
        String baseUrl = env.getProperty("POCKET_URL");
        String email = env.getProperty("POCKET_EMAIL");
        String password = env.getProperty("POCKET_PASSWORD");

        if (baseUrl == null || email == null || password == null) {
            throw new IllegalArgumentException("Required environment variables not configured");
        }
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(this::authFilter)
                .build();
    }
    private Mono<ClientResponse> authFilter(ClientRequest request, ExchangeFunction next) {
        if (authToken == null || isTokenExpired()) {
            return login()
                    .flatMap(token -> {
                        ClientRequest authenticatedRequest = ClientRequest.from(request)
                                .header("Authorization", token)
                                .build();
                        return next.exchange(authenticatedRequest);
                    });
        }
        ClientRequest authenticatedRequest = ClientRequest.from(request)
                .header("Authorization", authToken)
                .build();
        return next.exchange(authenticatedRequest);
    }
    private boolean isTokenExpired() {
        return tokenExpiration == null || LocalDateTime.now().isAfter(tokenExpiration);
    }
    private Mono<String> login() {
        String email = env.getProperty("POCKET_EMAIL");
        String password = env.getProperty("POCKET_PASSWORD");
        record LoginRequest(String identity, String password) {}
        record LoginResponse(String token) {}
        return WebClient.create(Objects.requireNonNull(env.getProperty("POCKET_URL")))
                .post()
                .uri("/api/collections/_superusers/auth-with-password")
                .bodyValue(new LoginRequest(email, password))
                .retrieve()
                .bodyToMono(LoginResponse.class)
                .map(response -> {
                    this.authToken = response.token();
                    this.tokenExpiration = LocalDateTime.now().plusDays(14);
                    log.info("Successfully authenticated with PocketBase");
                    return response.token();
                })
                .onErrorResume(e -> {
                    log.error("Failed to authenticate with PocketBase", e);
                    return Mono.error(new RuntimeException("Authentication failed", e));
                });
    }
}
