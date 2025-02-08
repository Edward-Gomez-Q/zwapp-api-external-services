package com.zwap.api.proxy_service.model;

public class ZwappUserModel {
    private String id;
    private String email;
    private Boolean verified;
    private String name;
    private String rol;
    private String empresa;
    private Boolean usuario_principal_empresa;

    public ZwappUserModel(String id, String email, Boolean verified, String name, String rol, String empresa, Boolean usuario_principal_empresa) {
        this.id = id;
        this.email = email;
        this.verified = verified;
        this.name = name;
        this.rol = rol;
        this.empresa = empresa;
        this.usuario_principal_empresa = usuario_principal_empresa;
    }

    public ZwappUserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Boolean getUsuario_principal_empresa() {
        return usuario_principal_empresa;
    }

    public void setUsuario_principal_empresa(Boolean usuario_principal_empresa) {
        this.usuario_principal_empresa = usuario_principal_empresa;
    }

    @Override
    public String toString() {
        return "ZwappUserModel{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", verified=" + verified +
                ", name='" + name + '\'' +
                ", rol='" + rol + '\'' +
                ", empresa='" + empresa + '\'' +
                ", usuario_principal_empresa=" + usuario_principal_empresa +
                '}';
    }
}
