package com.virtual.soft.axew.entity.enums;

public enum RoleEnum {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final int id;
    private final String code;

    RoleEnum (int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId () {
        return id;
    }

    public String getCode () {
        return code;
    }

    public static RoleEnum toEnum (int id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getId() == id) {
                return roleEnum;
            }
        }

        throw new IllegalArgumentException("Invalid RoleEnum");
    }

    @Override
    public String toString () {
        return "RoleEnum{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
