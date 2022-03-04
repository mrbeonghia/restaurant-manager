package com.salon.custom.dto.not_good;

import com.salon.custom.dto.staff.StaffChangeRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotGoodRequest {
    private Long id;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private Long staffId;
    private String feedback;
    private StaffChangeRequest staffChangeRequest;
    private Date assignedDate;
    private Date bookedDate;
}
