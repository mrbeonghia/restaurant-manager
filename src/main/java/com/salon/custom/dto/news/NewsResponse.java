package com.salon.custom.dto.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class NewsResponse extends BaseResponse {
    private NewsDTO news;
    private PageDto page;
    private List<NewsDTO> newsList;

    public NewsResponse() {
        setSuccess(Boolean.TRUE);
    }

    public NewsResponse(String message, int code) {
        super(message, code);
    }

}
