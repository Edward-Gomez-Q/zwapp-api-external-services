package com.zwap.api.proxy_service.dto;

import java.util.List;

public class EnlacePersonalizadoResponseStripeHeadersDto {
    private List<String> connection;
    private List<String> contentLength;
    private List<String> contentType;
    private List<String> date;
    private List<String> etag;
    private List<String> keepAlive;
    private List<String> xPoweredBy;

    public EnlacePersonalizadoResponseStripeHeadersDto(List<String> connection, List<String> contentLength, List<String> contentType, List<String> date, List<String> etag, List<String> keepAlive, List<String> xPoweredBy) {
        this.connection = connection;
        this.contentLength = contentLength;
        this.contentType = contentType;
        this.date = date;
        this.etag = etag;
        this.keepAlive = keepAlive;
        this.xPoweredBy = xPoweredBy;
    }
    public EnlacePersonalizadoResponseStripeHeadersDto() {
    }

    public List<String> getConnection() {
        return connection;
    }

    public void setConnection(List<String> connection) {
        this.connection = connection;
    }

    public List<String> getContentLength() {
        return contentLength;
    }

    public void setContentLength(List<String> contentLength) {
        this.contentLength = contentLength;
    }

    public List<String> getContentType() {
        return contentType;
    }

    public void setContentType(List<String> contentType) {
        this.contentType = contentType;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getEtag() {
        return etag;
    }

    public void setEtag(List<String> etag) {
        this.etag = etag;
    }

    public List<String> getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(List<String> keepAlive) {
        this.keepAlive = keepAlive;
    }

    public List<String> getxPoweredBy() {
        return xPoweredBy;
    }

    public void setxPoweredBy(List<String> xPoweredBy) {
        this.xPoweredBy = xPoweredBy;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseStripeHeadersDto{" +
                "connection=" + connection +
                ", contentLength=" + contentLength +
                ", contentType=" + contentType +
                ", date=" + date +
                ", etag=" + etag +
                ", keepAlive=" + keepAlive +
                ", xPoweredBy=" + xPoweredBy +
                '}';
    }
}
