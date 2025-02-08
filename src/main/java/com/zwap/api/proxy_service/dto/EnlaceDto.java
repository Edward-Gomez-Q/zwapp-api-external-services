package com.zwap.api.proxy_service.dto;

public class EnlaceDto {
    private String nombreEmpresa;
    private String url;

    public EnlaceDto(String nombreEmpresa, String url) {
        this.nombreEmpresa = nombreEmpresa;
        this.url = url;
    }
    public EnlaceDto() {
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "EnlaceDto{" +
                "nombreEmpresa='" + nombreEmpresa + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
