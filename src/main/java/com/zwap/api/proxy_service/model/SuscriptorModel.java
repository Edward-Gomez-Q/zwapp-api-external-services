package com.zwap.api.proxy_service.model;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SuscriptorModel {
    private String token;
    private String idEmpresa;
    private SseEmitter emitter;

    public SuscriptorModel(String token, String idEmpresa, SseEmitter emitter) {
        this.token = token;
        this.idEmpresa = idEmpresa;
        this.emitter = emitter;
    }

    public SuscriptorModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

    public void setEmitter(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public String toString() {
        return "SuscriptorModel{" +
                "token='" + token + '\'' +
                ", idEmpresa='" + idEmpresa + '\'' +
                ", emitter=" + emitter +
                '}';
    }
}
