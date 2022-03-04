package com.salon.custom.dto.booking;

import com.salon.custom.dto.combo.combo_order.ComboOrderDTO;
import com.salon.custom.dto.commercial.CommercialDTO;
import com.salon.custom.dto.coupon.CouponDTO;
import com.salon.custom.dto.store.StoreDTO;
import com.salon.custom.enums.StatusBooking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Long userId;
    private String customerName;
    private String customerPhone;
    private Long storeId;
    private String storeName;
    private StoreDTO storeDTO;
    private boolean chooseStaff;
    private Long staffId;
    private String staffName;
    private String dateOrder;
    private Long scheduleId;
    private Date startTime;
    private Date endTime;
    private Date startTimeBeforeCheckin;
    private Date endTimeBeforeCheckin;
    private Date timeFinishWorking;
    private Date timeFinishWorkingBeforeCheckin;
    private List<ComboOrderDTO> comboOrders;
    private String status;
    private boolean owner;
    private String note;
    private CouponDTO couponDTO;
    private Long price;
    private String uuid;
    private String qrImage;
    private List<OrderAccompanyDTO> orderAccompanies;
    private CommercialDTO commercialDTO;
    private Integer minutes;
    private String customerSex;
    private Long salary;
    private Integer restMinuteSurplus;
    private Integer districtId;
    private Integer cityId;
    private String address;
    private Date registrationDate;
    private Long storeFollowId;
    private String storeFollow;
    private String customerType;
    private String backgroundDisease;
    private Set<Long> staffIdsNG;
    private boolean isProlonged;
    private Date userCheckinTime;

//    public void setStatus(String status) {
//        this.status = status;
//        if (StatusBooking.ORDER.getStatus().equals(status)) {
//            this.status = "受付中";
//        }
//        if (StatusBooking.ACCEPT.getStatus().equals(status)) {
//            this.status = "確定";
//        }
//        if (StatusBooking.CANCEL.getStatus().equals(status)) {
//            this.status = "キャンセル";
//        }
//    }

    public BookingDTO(Long id, Long userId, String customerName, String customerPhone, Long storeId,
                      String storeName, boolean chooseStaff, Long staffId, String staffName, Long scheduleId,
                      Date startTime, Date endTime, Date timeFinishWorking,  String status, boolean owner, Long price,
                      String uuid, Integer minutes, String customerSex, Integer districtId, Integer cityId, String address,
                      Date registrationDate, Long storeFollowId, String customerType, String backgroundDisease, Date userCheckinTime) {
        this.id = id;
        this.userId = userId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.storeId = storeId;
        this.storeName = storeName;
        this.chooseStaff = chooseStaff;
        this.staffId = staffId;
        this.staffName = staffName;
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeFinishWorking = timeFinishWorking;
        this.status = status;
        this.owner = owner;
        this.price = price;
        this.uuid = uuid;
        this.minutes = minutes;
        this.customerSex = customerSex;
        this.districtId = districtId;
        this.cityId = cityId;
        this.address = address;
        this.registrationDate = registrationDate;
        this.storeFollowId = storeFollowId;
        this.customerType = customerType;
        this.backgroundDisease = backgroundDisease;
        this.userCheckinTime = userCheckinTime;
    }

    public BookingDTO(Date userCheckinTime) {
        this.userCheckinTime = userCheckinTime;
    }
}
