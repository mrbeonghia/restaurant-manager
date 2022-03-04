package com.salon.custom.service.excel;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OptionMaster {
    private Class<?> clazz;
    private Boolean printAll;
    private Map<String, FieldOption> fileNameToFileOption;

    public OptionMaster() {
        this.printAll = Boolean.FALSE;
        this.fileNameToFileOption = new HashMap<>();
    }
}
