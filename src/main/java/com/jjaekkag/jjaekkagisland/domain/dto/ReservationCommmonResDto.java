package com.jjaekkag.jjaekkagisland.domain.dto;

import lombok.Getter;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@Getter
public class ReservationCommmonResDto {
    private Long memberSeq;
    private Long reservationSeq;
    private Long lessonSeq;

    public ReservationCommmonResDto(Long memberSeq, Long reservationSeq, Long lessonSeq) {
        this.memberSeq = memberSeq;
        this.reservationSeq = reservationSeq;
        this.lessonSeq = lessonSeq;
    }

}
