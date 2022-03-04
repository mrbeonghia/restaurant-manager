package com.salon.custom.dto.combo.combo_favourite;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ComboFavouriteRequest {
    private String name;
    private Set<Long> comboPackIds;
}
