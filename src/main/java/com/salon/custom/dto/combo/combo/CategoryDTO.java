package com.salon.custom.dto.combo.combo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String categoryColor;
    private List<ComboDTO> comboDTOS;
}
