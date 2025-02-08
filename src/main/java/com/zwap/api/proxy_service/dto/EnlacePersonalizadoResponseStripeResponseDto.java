package com.zwap.api.proxy_service.dto;

import java.util.Map;

public class EnlacePersonalizadoResponseStripeResponseDto {
    private EnlacePersonalizadoResponseStripeJsonDto json;
    private EnlacePersonalizadoResponseStripeHeadersDto headers;
    private Map<String, String> cookies;
    private String raw;
    private Integer statusCode;

    public EnlacePersonalizadoResponseStripeResponseDto(EnlacePersonalizadoResponseStripeJsonDto json, EnlacePersonalizadoResponseStripeHeadersDto headers, Map<String, String> cookies, String raw, Integer statusCode) {
        this.json = json;
        this.headers = headers;
        this.cookies = cookies;
        this.raw = raw;
        this.statusCode = statusCode;
    }

    public EnlacePersonalizadoResponseStripeResponseDto() {
    }

    public EnlacePersonalizadoResponseStripeJsonDto getJson() {
        return json;
    }

    public void setJson(EnlacePersonalizadoResponseStripeJsonDto json) {
        this.json = json;
    }

    public EnlacePersonalizadoResponseStripeHeadersDto getHeaders() {
        return headers;
    }

    public void setHeaders(EnlacePersonalizadoResponseStripeHeadersDto headers) {
        this.headers = headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseStripeResponseDto{" +
                "json=" + json +
                ", headers=" + headers +
                ", cookies=" + cookies +
                ", raw='" + raw + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
