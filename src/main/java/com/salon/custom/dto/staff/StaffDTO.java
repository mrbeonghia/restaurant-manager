package com.salon.custom.dto.staff;

import com.salon.custom.dto.skill.SkillDTO;
import com.salon.custom.dto.staff.schedule.StaffScheduleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StaffDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String avatarUrl;
    private Long storeId;
    private Set<Long> storeIds;
    private String sex;
    private String phoneNumber;
    private String birthday;
    private Float ratingAverage;
    private String selfIntroduction;
    private List<SkillDTO> skills;
    private Boolean checkSchedule;
    private StaffScheduleDTO scheduleDTO;
    private Float performance;


    public StaffDTO(Long id, String name, String avatarUrl, String sex, String birthday) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.sex = sex;
        this.birthday = birthday;
    }

    public StaffDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
