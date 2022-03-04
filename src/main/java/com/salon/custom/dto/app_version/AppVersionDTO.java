package com.salon.custom.dto.app_version;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppVersionDTO {
    private String version;
    private String type;
    private boolean isUpdate;
    private String description;
    private String urlStore;
    private boolean forcedUpdate;
    private String appType;

}
