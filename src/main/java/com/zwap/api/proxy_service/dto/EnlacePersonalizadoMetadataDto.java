package com.zwap.api.proxy_service.dto;

public class EnlacePersonalizadoMetadataDto {
    private String userCompany;
    private String userEmail;
    private String userId;
    private String userPhone;

    public EnlacePersonalizadoMetadataDto(String userCompany, String userEmail, String userId, String userPhone) {
        this.userCompany = userCompany;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPhone = userPhone;
    }

    public EnlacePersonalizadoMetadataDto() {
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoMetadataDto{" +
                "userCompany='" + userCompany + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
