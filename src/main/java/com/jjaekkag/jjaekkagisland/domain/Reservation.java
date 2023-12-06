package com.jjaekkag.jjaekkagisland.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("false")
    private boolean cancelYn;

    @Builder
    public Reservation(Lesson lesson, Member member, int participant) {
        this.member = member;
        this.lesson = lesson;
        this.reservationStatus = ReservationStatus.RESERVATION;
        this.participant = participant;
    }

    public void updateCancelYn(boolean cancel){
        this.cancelYn = cancel;
    }

    public void updateResultStatus(ReservationStatus status) {
        this.reservationStatus = status;
    }
}
