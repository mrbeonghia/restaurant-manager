package com.salon.custom.dto.deep_link;

import java.util.ArrayList;
import java.util.List;

public class AssetLinkDto {
    private List<String> relation;
    private TargetDto target;

    public AssetLinkDto(TargetDto target) {
        List<String> strings = new ArrayList<>();
        strings.add("delegate_permission/common.handle_all_urls");
        this.relation = strings;
        this.target = target;
    }
}
