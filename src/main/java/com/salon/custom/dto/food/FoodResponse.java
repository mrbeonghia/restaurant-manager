package com.salon.custom.dto.food;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodResponse extends BaseResponse {
    private FoodDTO foodDTO;
    private List<FoodDTO> foodDTOS;
    private PageDto pageDto;

    public FoodResponse(String message, int code) {
        super(message, code);
    }

    public FoodResponse(List<FoodDTO> foodDTOS, PageDto pageDto) {
        this.foodDTOS = foodDTOS;
        this.pageDto = pageDto;
    }

    public FoodResponse(FoodDTO foodDTO) {
        this.foodDTO = foodDTO;
    }
}
