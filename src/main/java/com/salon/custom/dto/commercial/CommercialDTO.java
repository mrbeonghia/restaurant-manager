package com.salon.custom.dto.commercial;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommercialDTO {
    private Long id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String type;
    private String content;
    private String imageUrl;
    private String link;

}
