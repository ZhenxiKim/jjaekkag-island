package com.jjaekkag.jjaekkagisland.domain.dto;

import com.jjaekkag.jjaekkagisland.domain.Reservation;
import lombok.Getter;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@Getter
public class ReservationCommonResDto {

    private final Long memberSeq;
    private final Long reservationSeq;
    private final Long lessonSeq;

    public ReservationCommonResDto(Reservation reservation) {
        this.memberSeq = reservation.getMember().getMemberSeq();
        this.reservationSeq = reservation.getReservationSeq();
        this.lessonSeq = reservation.getLesson().getLessonSeq();
    }
}
