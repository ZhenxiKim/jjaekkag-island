package com.jjaekkag.jjaekkagisland.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jjaekkag.jjaekkagisland.domain.Lesson;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import com.jjaekkag.jjaekkagisland.domain.ReservationResult;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@Getter
public class ReservationResDto {

    private final String lessonName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalTime startTime;
    @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalTime endTime;
    private final Integer participant;

    public ReservationResDto(Reservation reservation) {

        Lesson lesson = reservation.getLesson();
        this.lessonName = lesson.getName();
        this.startDate = lesson.getStartDate();
        this.endDate = lesson.getEndDate();
        this.startTime = lesson.getStartTime();
        this.endTime = lesson.getEndTime();
        this.participant = reservation.getParticipant();
    }

}
