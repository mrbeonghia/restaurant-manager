package com.salon.custom.dto.commercial;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommercialPositionRequest {
    private List<List<Long>> commercialPosition;

}
