package com.salon.custom.dto.skill;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SkillResponse extends BaseResponse {
    private SkillDTO skillDTO;
    private List<SkillDTO> skillDTOS;
    private PageDto pageDto;

    public SkillResponse() {
        setSuccess(Boolean.TRUE);
    }

    public SkillResponse(String message, int code) {
        super(message, code);
    }

}
