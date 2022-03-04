package com.salon.custom.dto.slide;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SlidePositionRequest {
    private List<List<Long>> slidePosition;

}
