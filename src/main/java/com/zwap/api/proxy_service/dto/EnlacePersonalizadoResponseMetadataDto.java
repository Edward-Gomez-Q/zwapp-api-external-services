package com.zwap.api.proxy_service.dto;

public class EnlacePersonalizadoResponseMetadataDto {
    private String userCompany;
    private String userEmail;
    private String userId;
    private String userNumber;

    public EnlacePersonalizadoResponseMetadataDto(String userCompany, String userEmail, String userId, String userNumber) {
        this.userCompany = userCompany;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userNumber = userNumber;
    }

    public EnlacePersonalizadoResponseMetadataDto() {
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

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseMetadataDto{" +
                "userCompany='" + userCompany + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userId='" + userId + '\'' +
                ", userNumber='" + userNumber + '\'' +
                '}';
    }
}
