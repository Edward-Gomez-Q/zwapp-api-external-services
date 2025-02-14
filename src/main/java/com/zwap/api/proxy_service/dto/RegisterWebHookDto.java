package com.zwap.api.proxy_service.dto;

public class RegisterWebHookDto {
    private String url;
    public RegisterWebHookDto(String url) {
        this.url = url;
    }

    public RegisterWebHookDto() {
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RegisterWebHookDto{" +
                "url='" + url + '\'' +
                '}';
    }
}
