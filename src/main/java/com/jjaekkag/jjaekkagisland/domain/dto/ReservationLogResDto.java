package com.jjaekkag.jjaekkagisland.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jeongheekim
 * @date 12/5/23
 */
@RequiredArgsConstructor
@Getter
public class ReservationLogResDto {

    private final String email;

    private final String name;

    private final String lessonName;

    private final int participant;

    private final String reservationStatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservationDate;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime reservationTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate cancelDate;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime cancelTime;


    public ReservationLogResDto(Reservation reservation) {
        this.email = reservation.getMember().getEmail();
        this.name = reservation.getMember().getName();
        this.lessonName = reservation.getLesson().getName();
        this.participant = reservation.getParticipant();
        this.reservationStatus = reservation.getReservationStatus().getDescription();
        this.reservationDate = reservation.getReservationDate();
        this.reservationTime = reservation.getReservationTime();
        this.cancelDate = reservation.getCancelDate();
        this.cancelTime = reservation.getCancelTime();
    }
}
