package com.salon.custom.enums;

import java.util.List;

public enum StatusBooking {
    ORDER("order"),
    ACCEPT("accept"), // deprecated
    ARRIVE("arrive"),
    CANCEL("cancel"), // deprecated
    CANCEL_FREE("cancel_free"),
    CANCEL_CONTACT("cancel_contact"),
    CANCEL_NO_CONTACT("cancel_no_contact"),
    CANCEL_BY_STORE("cancel_by_store"),
    DONE("done");

    private String status;

    StatusBooking(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
