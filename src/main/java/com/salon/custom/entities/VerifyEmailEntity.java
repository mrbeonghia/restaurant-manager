package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "verify_email")
@Getter
@Setter
public class VerifyEmailEntity extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "verification_code", length = 32)
    private String verificationCode;

}
