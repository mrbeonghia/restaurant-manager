package com.salon.custom.dto.deep_link;

import java.util.ArrayList;
import java.util.List;

public class TargetDto {
    private String namespace;
    private String package_name;
    private List<String> sha256_cert_fingerprints;

    public TargetDto(String package_name, String sha256_cert_fingerprints) {
        this.namespace = "android_app";
        this.package_name = package_name;
        List<String> strings = new ArrayList<>(1);
        strings.add(sha256_cert_fingerprints);
        this.sha256_cert_fingerprints = strings;
    }
}
