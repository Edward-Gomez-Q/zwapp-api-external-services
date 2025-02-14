package com.zwap.api.proxy_service.dto;

import com.zwap.api.proxy_service.model.TransaccionModel;

public class RealtimeTransaccionDto {
    private String action;
    private TransaccionModel record;

    public RealtimeTransaccionDto(String action, TransaccionModel record) {
        this.action = action;
        this.record = record;
    }
    public RealtimeTransaccionDto() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TransaccionModel getRecord() {
        return record;
    }

    public void setRecord(TransaccionModel record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "RealtimeTransaccionDto{" +
                "action='" + action + '\'' +
                ", record=" + record +
                '}';
    }
}
