package com.salon.custom.dto.store;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import com.salon.custom.entities.StoreEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreResponse extends BaseResponse {
    private StoreDTO storeDTO;
    private List<StoreDTO> storeList;
    PageDto pageDto;

    public StoreResponse() {
        setSuccess(Boolean.TRUE);
        setCode(200);
    }

    public StoreResponse(String message, int code) {
        super(message, code);
    }

    public StoreResponse(StoreDTO storeDTO) {
        this.storeDTO = storeDTO;
        this.code = 200;
    }

    public StoreResponse(List<StoreDTO> storeList) {
        this.storeList = storeList;
        this.code = 200;
    }
}
