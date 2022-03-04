package com.salon.custom.dto.combo.combo_favourite;

import com.salon.custom.dto.combo.combo_pack.ComboPackDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComboFavouriteDTO {
    private Long storeId;
    private Long comboId;
    private List<ComboPackDTO> comboPackDTOS;
}
