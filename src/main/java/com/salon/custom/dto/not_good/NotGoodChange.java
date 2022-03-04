package com.salon.custom.dto.not_good;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotGoodChange {
    private Long notGoodId;
    private String oldFeedback;
    private String newFeedback;
    private Long staffIdChange;
}
