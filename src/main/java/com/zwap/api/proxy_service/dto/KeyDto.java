package com.zwap.api.proxy_service.dto;

import java.util.List;

public class KeyDto {
    private String name;
    private String key;
    private String url;
    private List<String> events;
    private String created_at;

    public KeyDto(String name, String key, String url, List<String> events, String created_at) {
        this.name = name;
        this.key = key;
        this.url = url;
        this.events = events;
        this.created_at = created_at;
    }
    public KeyDto(String name, String key, String url, List<String> events) {
        this.name = name;
        this.key = key;
        this.url = url;
        this.events = events;
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

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
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
                ", url='" + url + '\'' +
                ", events=" + events +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
