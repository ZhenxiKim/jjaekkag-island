package com.jjaekkag.jjaekkagisland.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jeongheekim
 * @date 12/2/23
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lesson")
public class Lesson extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonSeq;

    @NotNull(message = "수업명은 NotNull 필드 입니다.")
    @Size(min=1, max=50)
    private String name;

    private int limitParticipant;

    @OneToMany(mappedBy = "lesson")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_seq")
    private Store store;

    @NotNull(message = "수업 시작일은 NotNull 필드 입니다.")
    private LocalDate startDate;

    @NotNull(message = "수업 종료일은 NotNull 필드 입니다.")
    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

}
