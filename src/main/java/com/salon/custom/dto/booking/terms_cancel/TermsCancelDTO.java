package com.salon.custom.dto.booking.terms_cancel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TermsCancelDTO {
    private String description;

    public TermsCancelDTO(String description) {
        this.description = description;
    }
}
