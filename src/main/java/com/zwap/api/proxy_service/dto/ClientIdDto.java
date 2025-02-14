package com.zwap.api.proxy_service.dto;

public class ClientIdDto {
    private String clientId;
    public ClientIdDto(String clientId) {
        this.clientId = clientId;
    }
    public ClientIdDto() {
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    @Override
    public String toString() {
        return "clientId{" +
                "clientId='" + clientId + '\'' +
                '}';
    }
}
