package com.zwap.api.proxy_service.model;

public class EmpresaModel {
    private String id;
    private String collectionId;
    private String collectionName;
    private String nombre;
    private String principalContactoNombre;
    private String principalContactoCorreo;
    private String logo;
    private String ubicacion;
    private String descripcion;
    private String linkPermanentePago;
    private String principalContactoNumero;
    private String rubro;
    private String created;
    private String updated;

    public EmpresaModel(String id, String collectionId, String collectionName, String nombre, String principalContactoNombre, String principalContactoCorreo, String logo, String ubicacion, String descripcion, String linkPermanentePago, String principalContactoNumero, String rubro, String created, String updated) {
        this.id = id;
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.nombre = nombre;
        this.principalContactoNombre = principalContactoNombre;
        this.principalContactoCorreo = principalContactoCorreo;
        this.logo = logo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.linkPermanentePago = linkPermanentePago;
        this.principalContactoNumero = principalContactoNumero;
        this.rubro = rubro;
        this.created = created;
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrincipalContactoNombre() {
        return principalContactoNombre;
    }

    public void setPrincipalContactoNombre(String principalContactoNombre) {
        this.principalContactoNombre = principalContactoNombre;
    }

    public String getPrincipalContactoCorreo() {
        return principalContactoCorreo;
    }

    public void setPrincipalContactoCorreo(String principalContactoCorreo) {
        this.principalContactoCorreo = principalContactoCorreo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLinkPermanentePago() {
        return linkPermanentePago;
    }

    public void setLinkPermanentePago(String linkPermanentePago) {
        this.linkPermanentePago = linkPermanentePago;
    }

    public String getPrincipalContactoNumero() {
        return principalContactoNumero;
    }

    public void setPrincipalContactoNumero(String principalContactoNumero) {
        this.principalContactoNumero = principalContactoNumero;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "EmpresaModel{" +
                "id=" + id +
                ", collectionId='" + collectionId + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", nombre='" + nombre + '\'' +
                ", principalContactoNombre='" + principalContactoNombre + '\'' +
                ", principalContactoCorreo='" + principalContactoCorreo + '\'' +
                ", logo='" + logo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", linkPermanentePago='" + linkPermanentePago + '\'' +
                ", principalContactoNumero='" + principalContactoNumero + '\'' +
                ", rubro='" + rubro + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
