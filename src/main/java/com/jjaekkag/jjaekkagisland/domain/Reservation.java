package com.jjaekkag.jjaekkagisland.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "reservation")
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationSeq;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "lesson_seq")
    private Lesson lesson;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private int participant;

    @NotNull(message = "예약일은 NotNull 필드 입니다.")
    private LocalDate reservationDate;

    @NotNull(message = "예약시간은 NotNull 필드 입니다.")
    private LocalTime reservationTime;

    private LocalDate cancelDate;

    private LocalTime cancelTime;

    @Builder
    public Reservation(Lesson lesson, Member member, int participant) {
        this.member = member;
        this.lesson = lesson;
        this.reservationStatus = ReservationStatus.RESERVATION;
        this.participant = participant;
        this.reservationDate = LocalDate.now();
        this.reservationTime = LocalTime.now();
    }

    public void updateResultStatus(ReservationStatus status) {
        this.reservationStatus = status;
    }

    public void updateCancelDateTime() {
        this.cancelDate = LocalDate.now();
        this.cancelTime = LocalTime.now();
    }
}
