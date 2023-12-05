package com.jjaekkag.jjaekkagisland.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjaekkag.jjaekkagisland.domain.ReservationLogResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime updatedDate;

    public ReservationLogResDto(ReservationLogResult res) {
        this.email = res.getEmail();
        this.name = res.getName();
        this.lessonName = res.getLessonName();
        this.participant = res.getParticipant();
        this.reservationStatus = res.getReservationStatus();
        this.createdDate = res.getCreatedDate();
        this.updatedDate = res.getUpdatedDate();
    }
}
