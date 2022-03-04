package com.salon.custom.dto.history_change;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class HistoryChangeDTO {
    private String classify;
    private Long id;
    private Long staffIdChange;
    private String staffNameChange;
    private String typeChange;
    private String oldValue;
    private String newValue;
    private Long storeId;
}
