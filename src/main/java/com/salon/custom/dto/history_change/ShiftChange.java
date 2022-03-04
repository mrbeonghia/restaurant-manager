package com.salon.custom.dto.history_change;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftChange {
    private Long shiftId;
    private String oldValue;
    private String newValue;
    private Long staffIdChange;
    private String typeChange;
}
