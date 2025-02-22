package com.zwap.api.proxy_service.dto;

public class TransactionQueryParams {
    private Integer page;
    private Integer perPage;
    private String sort;
    private String filter;
    private String expand;
    private String fields;
    private Boolean skipTotal;

    public TransactionQueryParams(Integer page, Integer perPage, String sort, String filter, String expand, String fields, Boolean skipTotal) {
        this.page = page;
        this.perPage = perPage;
        this.sort = sort;
        this.filter = filter;
        this.expand = expand;
        this.fields = fields;
        this.skipTotal = skipTotal;
    }

    public TransactionQueryParams() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public Boolean getSkipTotal() {
        return skipTotal;
    }

    public void setSkipTotal(Boolean skipTotal) {
        this.skipTotal = skipTotal;
    }

    @Override
    public String toString() {
        return "TransactionQueryParams{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", sort='" + sort + '\'' +
                ", filter='" + filter + '\'' +
                ", expand='" + expand + '\'' +
                ", fields='" + fields + '\'' +
                ", skipTotal=" + skipTotal +
                '}';
    }
}
