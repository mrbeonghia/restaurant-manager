package com.salon.custom.dto.booking.staff_history;

import com.salon.custom.dto.combo.combo.ComboCombineDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StaffHistoryDTO {
    private Long bookingId;
    private Date bookingDate;
    private String storeName;
    private Long customerId;
    private String customerName;
    private String customerNonApp;
    private Long staffId;
    private String staffName;
    private Date startTime;
    private Date endTime;
    private Date endTimeRest;
    private List<ComboCombineDTO> comboCombineDTOS;
    private Boolean chooseStaff;
    private String commentOfStaff;
    private Long reviewId;
    private String commentOfCustomer;
    private Float ratingAvg;

    public StaffHistoryDTO(Long bookingId, Date bookingDate, String storeName, Long customerId, String customerName,
                           String customerNonApp, Boolean chooseStaff, String commentOfStaff,
                           Long reviewId, String commentOfCustomer, Float ratingAvg) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.storeName = storeName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerNonApp = customerNonApp;
        this.chooseStaff = chooseStaff;
        this.commentOfStaff = commentOfStaff;
        this.reviewId = reviewId;
        this.commentOfCustomer = commentOfCustomer;
        this.ratingAvg = ratingAvg;
    }

    public StaffHistoryDTO(Long bookingId, String storeName, Long customerId, String customerName, String customerNonApp,
                           Long staffId, String staffName, Date startTime, Date endTime, Boolean chooseStaff) {
        this.bookingId = bookingId;
        this.storeName = storeName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerNonApp = customerNonApp;
        this.staffId = staffId;
        this.staffName = staffName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.chooseStaff = chooseStaff;
    }
}
