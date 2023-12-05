package com.jjaekkag.jjaekkagisland.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@RequiredArgsConstructor
@Getter
public class ReservationHistoryDto {

    private String name;

    private int participant;

    private String cancel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime reservationDate;

    public ReservationHistoryDto(Reservation reservation) {
        Member member = reservation.getMember();
        this.name = member.getName();
        this.participant = reservation.getParticipant();
        if (reservation.isCancelYn()) {
            this.cancel = "예약 취소";
        }
        this.reservationDate = reservation.getCreatedDateTime();
    }


}
