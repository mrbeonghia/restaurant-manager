package com.salon.custom.dto.coupon;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CouponResponse extends BaseResponse {
    private CouponDTO couponDTO;
    private List<CouponDTO> couponDTOList;
    private PageDto pageDto;

    public CouponResponse(List<CouponDTO> couponDTOList, PageDto pageDto) {
        this.couponDTOList = couponDTOList;
        this.pageDto = pageDto;
    }

    public CouponResponse(String message, int code) {
        super(message, code);
    }

    public CouponDTO getCouponDTO() {
        return couponDTO;
    }

}
