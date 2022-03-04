package com.salon.custom.service.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class PrintService {

    public <T> void executePrint(List<PrePrintingField> fields, List<T> input) {
        List<String> headers = fields.stream().map(PrePrintingField::getHeader).collect(Collectors.toList());
        for (String header : headers) {
            log.info("header {}", header);
        }
        for (T t : input) {
            for (PrePrintingField field : fields) {
                try {

                    System.out.println(field.getGetter().invoke(t));
                    if (field.getFuncChange() != null) {
                        System.out.println(field.getFuncChange().apply(field.getGetter().invoke(t)));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    log.info("IllegalAccessException");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    log.info("InvocationTargetException");
                }
            }
        }
    }

    private List<PrePrintingField> getFieldsWillBePrinting(OptionMaster optionMaster) {
        var fieldOptionsMap = optionMaster.getFileNameToFileOption();
        Class<?> clazz = optionMaster.getClazz();

        List<String> fieldNameSkip = fieldOptionsMap.values()
                .stream().filter(FieldOption::getSkip)
                .map(FieldOption::getFieldName)
                .collect(Collectors.toList());

        if (optionMaster.getPrintAll()) {
            return getPrintingFieldCasePrintAll(fieldNameSkip, fieldOptionsMap, clazz);
        } else {
            return getPrintingFieldCaseNotPrintAll(fieldNameSkip, fieldOptionsMap, clazz);
        }
    }

    private List<PrePrintingField> getPrintingFieldCaseNotPrintAll(List<String> fieldNameSkip,
                                                                   Map<String, FieldOption> fieldOptionsMap,
                                                                   Class<?> clazz) {
        return fieldOptionsMap.values().stream()
                .filter(option -> !fieldNameSkip.contains(option.getFieldName()))
                .map(option -> {
                    String fieldName = option.getFieldName();
                    return new PrePrintingField(
                            fieldName,
                            fieldName,
                            extractGetterFromFieldName(clazz, fieldName),
                            option.getFuncChange()
                    );
                }).collect(Collectors.toList());
    }

    private List<PrePrintingField> getPrintingFieldCasePrintAll(List<String> fieldNameSkip,
                                                                Map<String, FieldOption> fieldOptionsMap,
                                                                Class<?> clazz) {

        return Stream.of(clazz.getDeclaredFields())
                .filter(field -> !fieldNameSkip.contains(field.getName()))
                .map(field -> {
                    String fieldName = field.getName();
                    if (fieldOptionsMap.containsKey(fieldName)) {
                        FieldOption option = fieldOptionsMap.get(fieldName);
                        return new PrePrintingField(
                                option,
                                extractGetterFromFieldName(clazz, fieldName));
                    } else {
                        return new PrePrintingField(
                                fieldName,
                                fieldName,
                                extractGetterFromFieldName(clazz, fieldName),
                                null
                        );
                    }
                }).collect(Collectors.toList());
    }

    private Method extractGetterFromFieldName(Class<?> clazz, String fieldName) {
        String startLetter = fieldName.substring(0, 1).toUpperCase();
        String getterName = "get" + startLetter + fieldName.substring(1);
        try {
            return clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.info("extract getter from field name \"{}\" fail", fieldName);

            return null;
        }
    }


}
