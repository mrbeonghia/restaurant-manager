package com.salon.custom.dto.not_good;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotGoodDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private String furiganaName;
    private String customerPhone;
    private Long staffId;
    private String staffName;
    private Long storeId;
    private String feedback;
    private Date assignedDate;
    private Date bookedDate;
    private String nameOfStaffCreate;

    public NotGoodDTO(Long id, Long staffId, String staffName, Long storeId) {
        this.id = id;
        this.staffId = staffId;
        this.staffName = staffName;
        this.storeId = storeId;
    }
}
