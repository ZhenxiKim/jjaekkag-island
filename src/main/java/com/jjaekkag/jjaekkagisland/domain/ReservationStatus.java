package com.jjaekkag.jjaekkagisland.domain;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public enum ReservationStatus {
    CANCEL("취소"), RESERVATION("예약");

    private String description;
    ReservationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
