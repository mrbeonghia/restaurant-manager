package com.salon.custom.dto.deep_link;

import java.util.ArrayList;
import java.util.List;

public class DetailApplinkcationLink {
    private String appID;
    private List<String> paths;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public DetailApplinkcationLink(String appID) {
        this.appID = appID;
        List<String> paths = new ArrayList<>();
        paths.add("*");
        this.paths = paths;
    }
}
