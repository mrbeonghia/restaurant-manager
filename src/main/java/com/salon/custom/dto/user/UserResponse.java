package com.salon.custom.dto.user;

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
public class UserResponse extends BaseResponse {
    private UserDTO user;
    private List<UserDTO> users;
    private String accessToken;

    private PageDto pageDto;

    public UserResponse() {
        setSuccess(Boolean.TRUE);
        setCode(200);
    }

    public UserResponse(String message, int code) {
        setSuccess(Boolean.FALSE);
        setMessage(message);
        setCode(code);
    }

    public UserResponse(UserDTO user) {
        this.user = user;
    }

    public UserResponse(List<UserDTO> users, PageDto pageDto) {
        this.users = users;
        this.pageDto = pageDto;
    }
}
