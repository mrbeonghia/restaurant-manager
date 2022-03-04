package com.salon.custom.dto.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushDeviceRequest {
    private Long id;
    private String os;
    private String token;

    @Override
    public String toString() {
        return "PushDeviceRequest{" +
                "id=" + id +
                ", os='" + os + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

}
