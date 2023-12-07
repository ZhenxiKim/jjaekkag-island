package com.jjaekkag.jjaekkagisland.domain;

import com.jjaekkag.jjaekkagisland.domain.dto.ReservationCommonResDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "reservation_log")
public class ReservationLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long memberSeq;
    private String memberName;

    private Long reservationSeq;

    private Long lessonSeq;
    private String lessonName;
    private int participant;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public ReservationLog(ReservationCommonResDto dto, ReservationStatus reservationStatus) {
        this.memberSeq = dto.getMemberSeq();
        this.memberName = dto.getMemberName();

        this.reservationSeq = dto.getReservationSeq();
        this.reservationStatus = reservationStatus;

        this.lessonSeq = dto.getLessonSeq();
        this.lessonName = dto.getLessonName();
        this.participant = dto.getParticipant();
        this.startDate = dto.getStartDate();
        this.startTime = dto.getStartTime();
        this.endDate = dto.getEndDate();
        this.endTime = dto.getEndTime();
    }
}
