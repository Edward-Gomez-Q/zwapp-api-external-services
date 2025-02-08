package com.zwap.api.proxy_service.dto;

import java.util.List;

public class EnlacePersonalizadoResponseStripeJsonDto {
    private Boolean adaptivePricingEnabled;
    private Integer amountSubtotal;
    private Integer amountTotal;
    private String cancelUrl;
    private Long created;
    private String currency;
    private Long expiresAt;
    private String id;
    private String mode;
    private String object;
    private String paymentStatus;
    private List<String> paymentMethodTypes;
    private String successUrl;
    private String uiMode;
    private String url;

    public EnlacePersonalizadoResponseStripeJsonDto(Boolean adaptivePricingEnabled, Integer amountSubtotal, Integer amountTotal, String cancelUrl, Long created, String currency, Long expiresAt, String id, String mode, String object, String paymentStatus, List<String> paymentMethodTypes, String successUrl, String uiMode, String url) {
        this.adaptivePricingEnabled = adaptivePricingEnabled;
        this.amountSubtotal = amountSubtotal;
        this.amountTotal = amountTotal;
        this.cancelUrl = cancelUrl;
        this.created = created;
        this.currency = currency;
        this.expiresAt = expiresAt;
        this.id = id;
        this.mode = mode;
        this.object = object;
        this.paymentStatus = paymentStatus;
        this.paymentMethodTypes = paymentMethodTypes;
        this.successUrl = successUrl;
        this.uiMode = uiMode;
        this.url = url;
    }
    public EnlacePersonalizadoResponseStripeJsonDto() {
    }

    public Boolean getAdaptivePricingEnabled() {
        return adaptivePricingEnabled;
    }

    public void setAdaptivePricingEnabled(Boolean adaptivePricingEnabled) {
        this.adaptivePricingEnabled = adaptivePricingEnabled;
    }

    public Integer getAmountSubtotal() {
        return amountSubtotal;
    }

    public void setAmountSubtotal(Integer amountSubtotal) {
        this.amountSubtotal = amountSubtotal;
    }

    public Integer getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Integer amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<String> getPaymentMethodTypes() {
        return paymentMethodTypes;
    }

    public void setPaymentMethodTypes(List<String> paymentMethodTypes) {
        this.paymentMethodTypes = paymentMethodTypes;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUiMode() {
        return uiMode;
    }

    public void setUiMode(String uiMode) {
        this.uiMode = uiMode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseStripeJsonDto{" +
                "adaptivePricingEnabled=" + adaptivePricingEnabled +
                ", amountSubtotal=" + amountSubtotal +
                ", amountTotal=" + amountTotal +
                ", cancelUrl='" + cancelUrl + '\'' +
                ", created=" + created +
                ", currency='" + currency + '\'' +
                ", expiresAt=" + expiresAt +
                ", id='" + id + '\'' +
                ", mode='" + mode + '\'' +
                ", object='" + object + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentMethodTypes=" + paymentMethodTypes +
                ", successUrl='" + successUrl + '\'' +
                ", uiMode='" + uiMode + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
