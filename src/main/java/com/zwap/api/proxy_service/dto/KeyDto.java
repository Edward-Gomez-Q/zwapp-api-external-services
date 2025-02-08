package com.zwap.api.proxy_service.dto;

public class KeyDto {
    private String name;
    private String key;
    private String created_at;

    public KeyDto(String name, String key, String created_at) {
        this.name = name;
        this.key = key;
        this.created_at = created_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "KeyDto{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
