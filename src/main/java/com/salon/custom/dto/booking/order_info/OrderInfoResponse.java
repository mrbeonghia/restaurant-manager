package com.salon.custom.dto.booking.order_info;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoResponse extends BaseResponse {
    private OrderInfoDTO orderInfoDTO;

    public OrderInfoResponse(String message, int code) {
        super(message, code);
    }

    public OrderInfoResponse(OrderInfoDTO orderInfoDTO) {
        this.orderInfoDTO = orderInfoDTO;
        this.code = 200;
    }
}
