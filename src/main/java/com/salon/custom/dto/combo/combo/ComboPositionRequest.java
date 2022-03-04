package com.salon.custom.dto.combo.combo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComboPositionRequest {
    private List<List<Long>> comboPosition;

}
