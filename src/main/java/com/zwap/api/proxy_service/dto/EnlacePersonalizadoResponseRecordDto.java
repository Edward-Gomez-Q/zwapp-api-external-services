package com.zwap.api.proxy_service.dto;

import java.util.List;

public class EnlacePersonalizadoResponseRecordDto {
    private Double amountTotal;
    private String collectionId;
    private String collectionName;
    private String created;
    private String createdStripe;
    private String currency;
    private String empresa;
    private String expiresAt;
    private String id;
    private String idStripe;
    private List<EnlacePersonalizadoItemsDto> items;
    private EnlacePersonalizadoResponseMetadataDto metadata;
    private String paymentStatus;
    private String recipient;
    private String status;
    private EnlacePersonalizadoResponseStripeResponseDto stripeResponse;
    private String updated;
    private String url;
    private String usuarioCreacion;

    public EnlacePersonalizadoResponseRecordDto(Double amountTotal, String collectionId, String collectionName, String created, String createdStripe, String currency, String empresa, String expiresAt, String id, String idStripe, List<EnlacePersonalizadoItemsDto> items, EnlacePersonalizadoResponseMetadataDto metadata, String paymentStatus, String recipient, String status, EnlacePersonalizadoResponseStripeResponseDto stripeResponse, String updated, String url, String usuarioCreacion) {
        this.amountTotal = amountTotal;
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.created = created;
        this.createdStripe = createdStripe;
        this.currency = currency;
        this.empresa = empresa;
        this.expiresAt = expiresAt;
        this.id = id;
        this.idStripe = idStripe;
        this.items = items;
        this.metadata = metadata;
        this.paymentStatus = paymentStatus;
        this.recipient = recipient;
        this.status = status;
        this.stripeResponse = stripeResponse;
        this.updated = updated;
        this.url = url;
        this.usuarioCreacion = usuarioCreacion;
    }
    public EnlacePersonalizadoResponseRecordDto() {
    }

    public Double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedStripe() {
        return createdStripe;
    }

    public void setCreatedStripe(String createdStripe) {
        this.createdStripe = createdStripe;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStripe() {
        return idStripe;
    }

    public void setIdStripe(String idStripe) {
        this.idStripe = idStripe;
    }

    public List<EnlacePersonalizadoItemsDto> getItems() {
        return items;
    }

    public void setItems(List<EnlacePersonalizadoItemsDto> items) {
        this.items = items;
    }

    public EnlacePersonalizadoResponseMetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadata(EnlacePersonalizadoResponseMetadataDto metadata) {
        this.metadata = metadata;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EnlacePersonalizadoResponseStripeResponseDto getStripeResponse() {
        return stripeResponse;
    }

    public void setStripeResponse(EnlacePersonalizadoResponseStripeResponseDto stripeResponse) {
        this.stripeResponse = stripeResponse;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseRecordDto{" +
                "amountTotal=" + amountTotal +
                ", collectionId='" + collectionId + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", created='" + created + '\'' +
                ", createdStripe='" + createdStripe + '\'' +
                ", currency='" + currency + '\'' +
                ", empresa='" + empresa + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                ", id='" + id + '\'' +
                ", idStripe='" + idStripe + '\'' +
                ", items=" + items +
                ", metadata=" + metadata +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", recipient='" + recipient + '\'' +
                ", status='" + status + '\'' +
                ", stripeResponse=" + stripeResponse +
                ", updated='" + updated + '\'' +
                ", url='" + url + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                '}';
    }
}
