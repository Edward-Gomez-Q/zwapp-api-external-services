package com.zwap.api.proxy_service.model;

public class LlaveModel {
    private String collectionId;
    private String collectionName;
    private String id;
    private String empresa;
    private String llave;
    private String nombre;
    private String created;
    private String updated;

    public LlaveModel(String collectionId, String collectionName, String id, String empresa, String llave, String nombre, String created, String updated) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.id = id;
        this.empresa = empresa;
        this.llave = llave;
        this.nombre = nombre;
        this.created = created;
        this.updated = updated;
    }

    public LlaveModel(String empresa, String llave, String nombre) {
        this.empresa = empresa;
        this.llave = llave;
        this.nombre = nombre;
    }

    public LlaveModel() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "LlaveModel{" +
                "collectionId='" + collectionId + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", id='" + id + '\'' +
                ", empresa='" + empresa + '\'' +
                ", llave='" + llave + '\'' +
                ", nombre='" + nombre + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
