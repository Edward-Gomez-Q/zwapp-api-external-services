package com.zwap.api.proxy_service.dto;

public class KeyDto {
    private String name;
    private String key;
    private String url;
    private String type;
    private String created_at;

    public KeyDto(String name, String key, String url, String type, String created_at) {
        this.name = name;
        this.key = key;
        this.url = url;
        this.type = type;
        this.created_at = created_at;
    }

    public KeyDto(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
    }

    public KeyDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "KeyDto{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
