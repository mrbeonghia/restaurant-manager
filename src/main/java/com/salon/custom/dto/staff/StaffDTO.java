package com.salon.custom.dto.staff;

import com.salon.custom.entities.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StaffDTO implements Serializable {
    private Long id;
    private String name;
    private String gender;
    private String avatarUrl;
    private String phoneNumber;
    private String birthday;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;

}
