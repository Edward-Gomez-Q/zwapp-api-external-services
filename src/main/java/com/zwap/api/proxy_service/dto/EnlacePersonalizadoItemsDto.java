package com.zwap.api.proxy_service.dto;

public class EnlacePersonalizadoItemsDto {
    private String currency;
    private String description;
    private String name;
    private Integer quantity;
    private Integer unit_amount;

    public EnlacePersonalizadoItemsDto(String currency, String description, String name, Integer quantity, Integer unit_amount) {
        this.currency = currency;
        this.description = description;
        this.name = name;
        this.quantity = quantity;
        this.unit_amount = unit_amount;
    }
    public EnlacePersonalizadoItemsDto() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnit_amount() {
        return unit_amount;
    }

    public void setUnit_amount(Integer unit_amount) {
        this.unit_amount = unit_amount;
    }

    @Override
    public String toString() {
        return "EnlacePersonalizadoItemsDto{" +
                "currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unit_amount=" + unit_amount +
                '}';
    }
}
