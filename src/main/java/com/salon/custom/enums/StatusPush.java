package com.salon.custom.enums;

public enum StatusPush {
    PENDING("pending"),
    SENDING("sending"),
    SUCCESS("success"),
    FAIL("fail"),
    PUSH_QUIET("push_quiet");

    private String name;

    StatusPush(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
