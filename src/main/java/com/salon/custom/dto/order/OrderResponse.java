package com.salon.custom.dto.order;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse extends BaseResponse {
    private List<OrderDTO> orderDTOS;
    private PageDto pageDto;

    public OrderResponse(List<OrderDTO> orderDTOS, PageDto pageDto) {
        this.orderDTOS = orderDTOS;
        this.pageDto = pageDto;
    }

    public OrderResponse(String message, int code) {
        super(message, code);
    }
}
