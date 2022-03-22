package com.salon.custom.dto.table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {
    private Long id;
    private String name;
    private Integer seat;
    private boolean available;
}
