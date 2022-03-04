package com.salon.custom.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long staffId;
    private String staffName;
    private Long storeId;
    private String storeName;
    private Integer rating1;
    private Integer rating2;
    private Integer rating3;
    private Integer rating4;
    private Integer rating5;
    private Integer rating6;
    private Float ratingAverage;
    private Long pointId;
    private Long pointReview;
    private String comment;
    private Date dateVisit;
    private Date dateReview;

    public ReviewDTO(Long id, Long customerId, String customerName, Long staffId, String staffName, Long storeId,
                     String storeName, Integer rating1, Integer rating2, Integer rating3, Integer rating4,
                     Integer rating5, Integer rating6, Float ratingAverage, String comment, Date dateReview) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.staffId = staffId;
        this.staffName = staffName;
        this.storeId = storeId;
        this.storeName = storeName;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.rating4 = rating4;
        this.rating5 = rating5;
        this.rating6 = rating6;
        this.ratingAverage = ratingAverage;
        this.comment = comment;
        this.dateReview = dateReview;
    }
}
