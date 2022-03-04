package com.salon.custom.dto.staff.staff_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptionistRequest {
    private Long id;
    private String email;
    private String password;
    private String name;

}
