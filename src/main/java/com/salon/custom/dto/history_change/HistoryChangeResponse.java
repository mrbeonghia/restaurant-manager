package com.salon.custom.dto.history_change;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistoryChangeResponse extends BaseResponse {
    private List<HistoryChangeDTO> historyChangeDTOS;
    private PageDto pageDto;

    public HistoryChangeResponse(List<HistoryChangeDTO> historyChangeDTOS, PageDto pageDto) {
        this.historyChangeDTOS = historyChangeDTOS;
        this.pageDto = pageDto;
        this.code = 200;
    }
}
