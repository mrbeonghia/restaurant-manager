package com.salon.custom.dto.combo.combo;

import com.salon.custom.dto.combo.combo_pack.ComboPackDTO;
import com.salon.custom.dto.combo.combo_pack.ComboPackRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ComboRequest {
    private Long id;
    private Long storeId;
    private String name;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private Boolean isProlonged;
    private Boolean isFavourite;
    private String color;
    private List<ComboPackRequest> comboPackRequests;

}
