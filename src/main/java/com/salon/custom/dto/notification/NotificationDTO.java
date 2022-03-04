package com.salon.custom.dto.notification;

import com.salon.custom.dto.staff.staff_user.StaffUserDTO;
import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.entities.StoreEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class NotificationDTO {
    private Long id;
    private String type;
    private String title;
    private String imageUrl;
    private String description;
    private String content;
    private Date time;
    private boolean all;
    private List<UserDTO> users;
    private List<StaffUserDTO> staffs;
    private Long storeId;
    private StoreEntity store;
    private Date startTime;
    private Date endTime;
    private Boolean seen;
    private Boolean read;
    private String statusPush;

}
