package com.zwap.api.proxy_service.dto;

public class EnlacePersonalizadoResponseDto {
    private String message;
    private EnlacePersonalizadoResponseRecordDto record;

    public EnlacePersonalizadoResponseDto(String message, EnlacePersonalizadoResponseRecordDto record) {
        this.message = message;
        this.record = record;
    }
    public EnlacePersonalizadoResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EnlacePersonalizadoResponseRecordDto getRecord() {
        return record;
    }

    public void setRecord(EnlacePersonalizadoResponseRecordDto record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoResponseDto{" +
                "message='" + message + '\'' +
                ", record=" + record +
                '}';
    }
}
