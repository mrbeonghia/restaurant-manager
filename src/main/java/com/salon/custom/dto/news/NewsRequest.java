package com.salon.custom.dto.news;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsRequest {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private String content;
    private Date time;
    private String type;

}
