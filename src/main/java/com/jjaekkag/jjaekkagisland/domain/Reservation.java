package com.jjaekkag.jjaekkagisland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@NoArgsConstructor
@Entity
@Getter
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationSeq;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_seq")
    private Lesson lesson;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private int participant;

    @ColumnDefault("false")
    private boolean cancelYn;

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
