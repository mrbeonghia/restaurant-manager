package com.salon.custom.dto.booking.order_info;

import com.salon.custom.dto.combo.combo.CategoryDTO;
import com.salon.custom.dto.combo.combo.ComboDTO;
import com.salon.custom.dto.coupon.CouponDTO;
import com.salon.custom.dto.store.StoreDTO;
import com.salon.custom.dto.user.UserPointDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInfoDTO {
    private StoreDTO storeDTO;
    private List<Integer> comboMinutes;
    private List<ComboDTO> comboFavourite;
    private List<ComboDTO> comboAll;
    private List<CategoryDTO> comboStore;
    private List<String> note;
    private List<CouponDTO> couponDTOS;
    private UserPointDTO userDTO;

}
