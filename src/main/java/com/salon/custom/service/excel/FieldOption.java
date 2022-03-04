package com.salon.custom.service.excel;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
public class FieldOption {
    private String fieldName;
    private String headerName;
    private Boolean skip;
    private Boolean isUpperCaseValue;
    private Function funcChange;
}
