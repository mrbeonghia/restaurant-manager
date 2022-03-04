package com.salon.custom.dto.booking.order_history;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import com.salon.custom.dto.not_good.NotGoodDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingHistoryResponse extends BaseResponse {
    private OrderHistoryDTO orderHistoryDTO;
    private List<OrderHistoryDTO> orderHistoryDTOS;
    private List<String> staffNotGood;
    private String termsCancel;
    private PageDto pageDto;

    public BookingHistoryResponse(String message, int code) {
        super(message, code);
    }

    public BookingHistoryResponse(List<OrderHistoryDTO> orderHistoryDTOS,List<String> staffNotGood, PageDto pageDto) {
        this.orderHistoryDTOS = orderHistoryDTOS;
        this.staffNotGood = staffNotGood;
        this.pageDto = pageDto;
        this.code = 200;
    }

    public BookingHistoryResponse(List<OrderHistoryDTO> orderHistoryDTOS, String termsCancel, PageDto pageDto) {
        this.orderHistoryDTOS = orderHistoryDTOS;
        this.termsCancel = termsCancel;
        this.pageDto = pageDto;
        this.code = 200;
    }

    public BookingHistoryResponse(List<OrderHistoryDTO> orderHistoryDTOS, PageDto pageDto) {
        this.orderHistoryDTOS = orderHistoryDTOS;
        this.pageDto = pageDto;
        this.code = 200;
    }

    public BookingHistoryResponse() {
        this.code = 200;
    }
}
