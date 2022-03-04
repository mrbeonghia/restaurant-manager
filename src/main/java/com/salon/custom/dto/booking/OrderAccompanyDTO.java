package com.salon.custom.dto.booking;

import com.salon.custom.dto.combo.combo_order.ComboOrderDTO;
import com.salon.custom.dto.combo.combo_order.ComboOrderFavouriteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderAccompanyDTO {
    private Long id;
    private Long userId;
    private Long staffId;
    private String staffName;
    private String customerName;
    private String customerPhone;
    private List<ComboOrderDTO> comboOrders;
    private Date startTime;
    private Date endTime;
    private Integer minutes;
    private String note;
    private Long price;
    private Boolean chooseStaff;
}
