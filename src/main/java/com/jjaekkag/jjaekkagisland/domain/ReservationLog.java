package com.jjaekkag.jjaekkagisland.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@NoArgsConstructor
@Entity
@Getter
public class ReservationLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Long memberSeq;

    private Long reservationSeq;

    private Long lessonSeq;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public ReservationLog(Long memberSeq, Long reservationSeq, Long lessonSeq, ReservationStatus reservationStatus) {
        this.memberSeq = memberSeq;
        this.reservationSeq = reservationSeq;
        this.lessonSeq = lessonSeq;
        this.reservationStatus = reservationStatus;
    }
}
