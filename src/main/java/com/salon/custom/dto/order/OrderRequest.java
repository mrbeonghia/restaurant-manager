package com.salon.custom.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Long id;
    private Long foodId;
    private Integer quantity;
    private String status;
}
