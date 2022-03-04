package com.salon.custom.dto.deep_link;

import java.util.ArrayList;
import java.util.List;

public class ApplicationLink {
    private List<String> apps;
    private List<DetailApplinkcationLink> details;

    public ApplicationLink(List<DetailApplinkcationLink> details) {
        this.apps = new ArrayList<>();
        this.details = details;
    }
}
