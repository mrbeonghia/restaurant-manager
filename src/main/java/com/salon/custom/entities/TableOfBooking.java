package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "table_of_booking")
@Getter
@Setter
public class TableOfBooking extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name="table_id")
    private TableEntity tableEntity;
}
