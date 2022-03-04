package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "terms_cancel")
@Getter
@Setter
public class TermsCancel extends BaseEntity {
    private String description;
}
