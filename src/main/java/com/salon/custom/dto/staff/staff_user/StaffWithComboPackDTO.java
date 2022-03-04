package com.salon.custom.dto.staff.staff_user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StaffWithComboPackDTO {
    private Long staffId;
    private String sex;
    private String name;
    private String avatarUrl;
    private Long comboPackId;

    public StaffWithComboPackDTO(Long staffId, String sex, String name, String avatarUrl, Long comboPackId) {
        this.staffId = staffId;
        this.sex = sex;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.comboPackId = comboPackId;
    }
}
