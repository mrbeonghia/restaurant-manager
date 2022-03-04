package com.salon.custom.dto.slide;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SlideResponse extends BaseResponse {

    private SlideDTO slide;

    private List<SlideDTO> slides;

}
