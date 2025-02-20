package com.zwap.api.proxy_service.model;

public class TransaccionModel {
    private String collectionId;
    private String collectionName;
    private String sessionId;
    private Object stripeResponse;


    private String id;
    private Double amount;
    private String empresa;
    private String description;
    private String clientName;
    private String clientEmail;
    private String clientCountry;
    private String currency;
    private String paymentStatus;
    private String paymentLinkId;
    private String createdAt;
    private String paidAt;
    private String paymentIntent;
    private String voucherUrl;
    private String charge;
    private String availableOn;
    private String posPayment;
    private String created;
    private String updated;

    public TransaccionModel(String collectionId, String collectionName, String sessionId, Object stripeResponse, String id, Double amount, String empresa, String description, String clientName, String clientEmail, String clientCountry, String currency, String paymentStatus, String paymentLinkId, String createdAt, String paidAt, String paymentIntent, String voucherUrl, String charge, String availableOn, String posPayment, String created, String updated) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.sessionId = sessionId;
        this.stripeResponse = stripeResponse;
        this.id = id;
        this.amount = amount;
        this.empresa = empresa;
        this.description = description;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientCountry = clientCountry;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
        this.paymentLinkId = paymentLinkId;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.paymentIntent = paymentIntent;
        this.voucherUrl = voucherUrl;
        this.charge = charge;
        this.availableOn = availableOn;
        this.posPayment = posPayment;
        this.created = created;
        this.updated = updated;
    }

    public TransaccionModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Object getStripeResponse() {
        return stripeResponse;
    }

    public void setStripeResponse(Object stripeResponse) {
        this.stripeResponse = stripeResponse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public void setPaymentLinkId(String paymentLinkId) {
        this.paymentLinkId = paymentLinkId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public String getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public String getVoucherUrl() {
        return voucherUrl;
    }

    public void setVoucherUrl(String voucherUrl) {
        this.voucherUrl = voucherUrl;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getAvailableOn() {
        return availableOn;
    }

    public void setAvailableOn(String availableOn) {
        this.availableOn = availableOn;
    }

    public String getPosPayment() {
        return posPayment;
    }

    public void setPosPayment(String posPayment) {
        this.posPayment = posPayment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "TransaccionModel{" +
                "collectionId='" + collectionId + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", stripeResponse='" + stripeResponse + '\'' +
                ", id='" + id + '\'' +
                ", amount=" + amount +
                ", empresa='" + empresa + '\'' +
                ", description='" + description + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientCountry='" + clientCountry + '\'' +
                ", currency='" + currency + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentLinkId='" + paymentLinkId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", paidAt='" + paidAt + '\'' +
                ", paymentIntent='" + paymentIntent + '\'' +
                ", voucherUrl='" + voucherUrl + '\'' +
                ", charge='" + charge + '\'' +
                ", availableOn='" + availableOn + '\'' +
                ", posPayment='" + posPayment + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
