package com.salon.custom.dto.store;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChangePositionRequest {

    private List<List<Long>> storePosition;


    @Override
    public String toString() {
        return "ChangePositionRequest{" +
                "storePosition=" + storePosition +
                '}';
    }
}
