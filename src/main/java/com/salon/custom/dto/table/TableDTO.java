package com.salon.custom.dto.table;

import lombok.Data;

@Data
public class TableDTO {
    private Long id;
    private String name;
    private Integer seat;
    private boolean available;
}
