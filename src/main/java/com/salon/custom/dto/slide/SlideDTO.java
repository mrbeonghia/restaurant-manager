package com.salon.custom.dto.slide;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class SlideDTO {
    private Long id;

    private String type;

    private String imageUrl;

    private String link;

    private String title;

    private Date startTime;

    private Date endTime;

    private String content;

    private Long newsId;

}
