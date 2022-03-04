package com.salon.custom.service.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.function.Function;

@Setter
@Getter
@AllArgsConstructor
public class PrePrintingField {
    private String header;
    private String fieldName;
    private Method getter;
    private Function funcChange;

    public PrePrintingField(FieldOption option, Method getter) {
        this.header = option.getHeaderName();
        this.fieldName = option.getFieldName();
        this.funcChange = option.getFuncChange();
    }
}
