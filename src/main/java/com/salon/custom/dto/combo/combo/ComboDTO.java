package com.salon.custom.dto.combo.combo;

import com.salon.custom.dto.combo.combo_favourite.ComboPackFavourite;
import com.salon.custom.dto.combo.combo_pack.ComboPackDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ComboDTO {
    private Long id;
    private Long storeId;
    private String name;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;
    private String categoryColor;
    private Boolean isProlonged;
    private Boolean isFavourite;
    private String color;
    private List<ComboPackDTO> comboPackDTOS;

    public ComboDTO(List<ComboPackDTO> comboPackDTOS) {
        this.comboPackDTOS = comboPackDTOS;
    }
}
