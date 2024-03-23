package com.virtual.soft.axew.entity.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1, "WAITING_PAYMENT"),
    PAID(2, "PAID"),
    SHIPPED(3, "SHIPPED"),
    DELIVERED(4, "DELIVERED"),
    CANCELED(5, "CANCELED");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus toEnum(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
