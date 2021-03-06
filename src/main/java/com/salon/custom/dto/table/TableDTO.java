package com.salon.custom.dto.table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableDTO {
    private Long id;
    private String name;
    private Integer seat;
    private boolean available;
}
