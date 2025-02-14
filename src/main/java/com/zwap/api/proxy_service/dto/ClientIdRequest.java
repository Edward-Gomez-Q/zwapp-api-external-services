package com.zwap.api.proxy_service.dto;

import java.util.List;

public class ClientIdRequest extends ClientIdDto {

    private List<String> subscriptions;
    public ClientIdRequest() {
    }
    public ClientIdRequest(String clientId, List<String> subscriptions) {
        super(clientId);
        this.subscriptions = subscriptions;
    }
    public List<String> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(List<String> subscriptions) {
        this.subscriptions = subscriptions;
    }
    @Override
    public String toString() {
        return "ClientIdRequest{" +
                "subscriptions='" + subscriptions + '\'' +
                '}';
    }
}
