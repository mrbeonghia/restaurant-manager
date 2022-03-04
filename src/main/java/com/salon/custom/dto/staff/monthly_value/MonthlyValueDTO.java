package com.salon.custom.dto.staff.monthly_value;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyValueDTO {
    private Long staffId;
    private Integer month;
    private Integer year;
    private Long salary;
    private Float performance;
    private Integer numberOfCustomers;
    private Integer customerSpecified;
    private String timeBonus;
    private Long salaryBonus;
    private Double rating1;
    private Double rating2;
    private Double rating3;
    private Double rating4;
    private Double rating5;
    private Double rating6;
    private Double ratingAvg;
    private Double rating1All;
    private Double rating2All;
    private Double rating3All;
    private Double rating4All;
    private Double rating5All;
    private Double rating6All;
    private Double ratingAvgAll;


    public MonthlyValueDTO(Long staffId, Double rating1, Double rating2, Double rating3, Double rating4, Double rating5, Double rating6, Double ratingAverage) {
        this.staffId = staffId;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.rating4 = rating4;
        this.rating5 = rating5;
        this.rating6 = rating6;
        this.ratingAvg = ratingAverage;
    }

    public MonthlyValueDTO(Double rating1All, Double rating2All, Double rating3All, Double rating4All, Double rating5All, Double rating6All, Double ratingAvgAll) {
        this.rating1All = rating1All;
        this.rating2All = rating2All;
        this.rating3All = rating3All;
        this.rating4All = rating4All;
        this.rating5All = rating5All;
        this.rating6All = rating6All;
        this.ratingAvgAll = ratingAvgAll;
    }
}
