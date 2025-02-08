package com.zwap.api.proxy_service.dto;

import java.util.List;

public class EnlacePersonalizadoDto {
    private String cancel_url;
    private List<EnlacePersonalizadoItemsDto> items;
    private EnlacePersonalizadoMetadataDto metadata;
    private String recipient;
    private String success_url;
    private String userId;

    public EnlacePersonalizadoDto(String cancel_url, List<EnlacePersonalizadoItemsDto> items, EnlacePersonalizadoMetadataDto metadata, String recipient, String success_url, String userId) {
        this.cancel_url = cancel_url;
        this.items = items;
        this.metadata = metadata;
        this.recipient = recipient;
        this.success_url = success_url;
        this.userId = userId;
    }
    public EnlacePersonalizadoDto(String recipient, List<EnlacePersonalizadoItemsDto> items) {
        this.recipient = recipient;
        this.items = items;
    }

    public EnlacePersonalizadoDto() {
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }

    public List<EnlacePersonalizadoItemsDto> getItems() {
        return items;
    }

    public void setItems(List<EnlacePersonalizadoItemsDto> items) {
        this.items = items;
    }

    public EnlacePersonalizadoMetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadata(EnlacePersonalizadoMetadataDto metadata) {
        this.metadata = metadata;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSuccess_url() {
        return success_url;
    }

    public void setSuccess_url(String success_url) {
        this.success_url = success_url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoDto{" +
                "cancel_url='" + cancel_url + '\'' +
                ", items=" + items +
                ", metadata=" + metadata +
                ", recipient='" + recipient + '\'' +
                ", success_url='" + success_url + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
