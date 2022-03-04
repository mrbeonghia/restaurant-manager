package com.salon.custom.dto.booking.request;

import com.salon.custom.util.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
public class BookingRequest {
    private Long id;
    @ApiModelProperty(hidden = true)
    private Long storeId;
    private Long staffId;
    private String email;
    private String customerPhone;
    private String customerName;
    private boolean assigned;
    private boolean owner;
    private Integer minutes;
    private boolean comboFavourite;
    private Set<Long> comboPackIds;
    @ApiModelProperty(hidden = true)
    private Date startTime;
    @ApiModelProperty(hidden = true)
    private Date timeFinishWorking;
    private String note;
    private Long couponId;
    private Long pointUse;
    private ComboOption comboOption;
    private Long price;
    private String status;
    private String commentOfStaff;



    public void selfPopulate(Date startTime, Long storeId) {
        this.startTime = DateUtils.removeSecond(startTime);
        this.storeId = storeId;
        this.timeFinishWorking = DateUtils.plus(startTime, Calendar.MINUTE, minutes);
    }
}
