package com.jjaekkag.jjaekkagisland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jeongheekim
 * @date 12/2/23
 */
@Entity
@Getter
public class Lesson extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonSeq;

    @NotNull(message = "NotNull 필드 입니다.")
    @Size(min=1, max=50)
    private String name;

    private int limitParticipant;//TODO 20값

    @OneToMany(mappedBy = "lesson")
    private List<Reservation> reservations = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_seq")
    private Store store;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
