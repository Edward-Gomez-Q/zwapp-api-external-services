package com.zwap.api.proxy_service.dto;

import java.util.List;

public class EnlacePersonalizadoResponseRecordDto {
    private String created;
    private Double amount_total;
    private String currency;
    private String expires_at;
    private String id;
    private List<EnlacePersonalizadoItemsDto> items;
    private EnlacePersonalizadoResponseMetadataDto metadata;
    private String recipient;
    private String status;
    private String url;

    public EnlacePersonalizadoResponseRecordDto(String created, Double amount_total, String currency, String expires_at, String id, List<EnlacePersonalizadoItemsDto> items, EnlacePersonalizadoResponseMetadataDto metadata, String recipient, String status, String url) {
        this.created = created;
        this.amount_total = amount_total;
        this.currency = currency;
        this.expires_at = expires_at;
        this.id = id;
        this.items = items;
        this.metadata = metadata;
        this.recipient = recipient;
        this.status = status;
        this.url = url;
    }
    public EnlacePersonalizadoResponseRecordDto() {
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Double getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(Double amount_total) {
        this.amount_total = amount_total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseRecordDto{" +
                "created='" + created + '\'' +
                "amount_total=" + amount_total +
                ", currency='" + currency + '\'' +
                ", expires_at='" + expires_at + '\'' +
                ", id='" + id + '\'' +
                ", items=" + items +
                ", metadata=" + metadata +
                ", recipient='" + recipient + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
