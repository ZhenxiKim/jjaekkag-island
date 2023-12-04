package com.jjaekkag.jjaekkagisland.domain.dto;

import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@RequiredArgsConstructor
@Getter
public class EnrolledMemberResDto {

    private final String email;
    private final String name;
    private final int participant;
    private final LocalDateTime enrolledDateTime;

    public EnrolledMemberResDto (Reservation reservation) {
        Member member = reservation.getMember();
        this.email = member.getEmail();
        this.name = member.getName();
        this.participant = reservation.getParticipant();
        this.enrolledDateTime = reservation.getCreatedDateTime();
    }
}
