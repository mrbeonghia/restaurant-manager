package com.salon.custom.dto.staff.staff_user;

import com.salon.custom.dto.combo.combo.ComboStoreDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StaffUserDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String sex;
    private String password;
    private String avatarUrl;
    private Long comboPackId;
    private Long shiftId;
    private List<ComboStoreDTO> comboStoreDTOS;

    public StaffUserDTO(Long id, String name, String phoneNumber, String sex, String avatarUrl,
                        Long comboPackId, Long shiftId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.avatarUrl = avatarUrl;
        this.comboPackId = comboPackId;
        this.shiftId = shiftId;
    }

    public StaffUserDTO(Long id, String name, String phoneNumber, String sex, String avatarUrl,
                        Long comboPackId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.avatarUrl = avatarUrl;
        this.comboPackId = comboPackId;
    }
}
