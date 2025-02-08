package com.zwap.api.proxy_service.dto;

import java.util.List;

public class PocketBaseResponseDto<T>{
    private int page;
    private int perPage;
    private int totalPages;
    private int totalItems;
    private T items;

    public PocketBaseResponseDto(int page, int perPage, int totalPages, int totalItems, T items) {
        this.page = page;
        this.perPage = perPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.items = items;
    }
    public PocketBaseResponseDto() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PocketBaseResponseDto{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
