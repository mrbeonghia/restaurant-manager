package com.salon.custom.dto.staff.staff_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptionistDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Long storeId;

}
