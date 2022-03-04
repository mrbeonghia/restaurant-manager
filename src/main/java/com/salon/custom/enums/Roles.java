package com.salon.custom.enums;

public enum Roles {
    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    APP_USER("APP_USER"),
    STORE_ADMIN("STORE_ADMIN"),
    STAFF_ADMIN("STAFF_ADMIN"),
    RECEPTIONIST("RECEPTIONIST"),
    STAFF("STAFF");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
