package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import com.salon.custom.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auth_event")
@Getter
@Setter
public class AuthenticationEventEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_logout")
    private Boolean isLogout;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "data", columnDefinition = "TEXT")
    private String data;

    @Column(name = "timeout")
    private long timeout;

    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;


    public Boolean getLogout() {
        return isLogout;
    }

    public void setLogout(Boolean logout) {
        isLogout = logout;
    }

}
