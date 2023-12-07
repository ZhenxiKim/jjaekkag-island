package com.jjaekkag.jjaekkagisland.domain.dto;

import com.jjaekkag.jjaekkagisland.domain.Lesson;
import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@Getter
public class ReservationCommonResDto {

    private final Long memberSeq;
    private final String memberName;
    private final Long reservationSeq;
    private final Long lessonSeq;
    private final String lessonName;
    private final int participant;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public ReservationCommonResDto(Reservation reservation) {
        Member member = reservation.getMember();
        Lesson lesson = reservation.getLesson();
        this.memberSeq = member.getMemberSeq();
        this.memberName = member.getName();

        this.reservationSeq = reservation.getReservationSeq();
        this.participant = reservation.getParticipant();

        this.lessonName = lesson.getName();
        this.lessonSeq = lesson.getLessonSeq();
        this.startDate = lesson.getStartDate();
        this.startTime = lesson.getStartTime();
        this.endDate = lesson.getEndDate();
        this.endTime = lesson.getEndTime();
    }
}
