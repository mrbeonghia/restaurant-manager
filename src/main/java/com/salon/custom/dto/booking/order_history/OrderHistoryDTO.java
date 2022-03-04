package com.salon.custom.dto.booking.order_history;

import com.salon.custom.dto.combo.combo_order.ComboOrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderHistoryDTO {
    private Long orderId;
    private String status;
    private Date startTime;
    private Long storeId;
    private String storeName;
    private String storePhone;
    private String storeUrl;
    private boolean chooseStaff;
    private Long staffId;
    private String staffName;
    private Long pointReview;
    private Long reviewId;
    private boolean reviewed;
    private Float ratingAverage;
    private String reviewComment;
    private Long gachaId;
    private boolean spinGacha;
    private boolean enableSpinGacha;
    private String commentOfStaff;
    private List<ComboOrderDTO> comboOrders;
    private Long customerId;
    private String customerName;
    private String furiganaName;
    private Date userCheckinTime;
    private Date userCheckoutTime;
    private Long price;
    private Long storeWage;

    public OrderHistoryDTO(Long orderId, String status, Date startTime, Long storeId, String storeName, String storePhone,
                           String storeUrl, boolean chooseStaff, Long staffId, String staffName, Long pointReview,
                           String commentOfStaff, Long reviewId, Long gachaId, Float ratingAverage, String reviewComment,
                           Long price) {
        this.orderId = orderId;
        this.status = status;
        this.startTime = startTime;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storePhone = storePhone;
        this.storeUrl = storeUrl;
        this.chooseStaff = chooseStaff;
        this.staffId = staffId;
        this.staffName = staffName;
        this.pointReview = pointReview;
        this.commentOfStaff = commentOfStaff;
        this.reviewId = reviewId;
        this.gachaId = gachaId;
        this.ratingAverage = ratingAverage;
        this.reviewComment = reviewComment;
        this.price = price;
    }

    public OrderHistoryDTO(Long orderId, String status, Date startTime, Long storeId, boolean chooseStaff,
                           Long staffId, String staffName, String commentOfStaff, Long customerId,
                           String customerName, String furiganaName, Date userCheckinTime, Date userCheckoutTime,
                           Long price) {
        this.orderId = orderId;
        this.status = status;
        this.startTime = startTime;
        this.storeId = storeId;
        this.chooseStaff = chooseStaff;
        this.staffId = staffId;
        this.staffName = staffName;
        this.commentOfStaff = commentOfStaff;
        this.customerId = customerId;
        this.customerName = customerName;
        this.furiganaName = furiganaName;
        this.userCheckinTime = userCheckinTime;
        this.userCheckoutTime = userCheckoutTime;
        this.price = price;
    }

    public OrderHistoryDTO(Long orderId, Long storeId, Date startTime, Long gachaId) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.startTime = startTime;
        this.gachaId = gachaId;
    }

}
