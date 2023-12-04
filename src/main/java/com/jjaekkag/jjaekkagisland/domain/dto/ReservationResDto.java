package com.jjaekkag.jjaekkagisland.domain.dto;

import com.jjaekkag.jjaekkagisland.domain.ReservationResult;
import lombok.Getter;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@Getter
public class ReservationResDto {

    private final String storeName;
    private final String address;
    private final String name;
    private final Integer participant;

    public ReservationResDto(ReservationResult res) {
        this.storeName = res.getStoreName();
        this.address = res.getAddress();
        this.name = res.getName();
        this.participant = res.getParticipant();
    }

}
