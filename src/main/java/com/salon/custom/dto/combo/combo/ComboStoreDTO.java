package com.salon.custom.dto.combo.combo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComboStoreDTO {
    private Long storeId;
    private String storeName;
    private List<ComboDTO> comboDTOS;
}
