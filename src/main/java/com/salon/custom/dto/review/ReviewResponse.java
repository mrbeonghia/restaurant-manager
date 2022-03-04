package com.salon.custom.dto.review;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewResponse extends BaseResponse {
    private ReviewDTO reviewDTO;
    private List<ReviewDTO> reviewDTOS;
    private PageDto pageDto;
    public ReviewResponse() {
        setSuccess(Boolean.TRUE);
    }

    public ReviewResponse(String message, int code) {
        super(message, code);
    }

}
