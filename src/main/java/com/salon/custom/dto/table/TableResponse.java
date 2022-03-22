package com.salon.custom.dto.table;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableResponse extends BaseResponse {
    private TableDTO tableDTO;
    private List<TableDTO> tableDTOS;
    private PageDto pageDto;

    public TableResponse(List<TableDTO> tableDTOS, PageDto pageDto) {
        this.tableDTOS = tableDTOS;
        this.pageDto = pageDto;
    }

    public TableResponse(String message, int code) {
        super(message, code);
    }

    public TableResponse(TableDTO tableDTO) {
        this.tableDTO = tableDTO;
    }
}
