package com.salon.custom.dto.staff;

import com.salon.custom.dto.skill.SkillRequest;
import com.salon.custom.dto.staff.schedule.StaffScheduleRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StaffRequest {
    private Long staffId;
    private String name;
    private String email;
    private String avatarUrl;
    private Set<Long> storeIds;
    private String sex;
    private String phoneNumber;
    private String birthday;
    /*private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;*/
    private String selfIntroduction;
    private List<SkillRequest> skillRequests;
    private List<StaffScheduleRequest> staffScheduleRequests;
    private Set<Long> comboPackIds;

}
