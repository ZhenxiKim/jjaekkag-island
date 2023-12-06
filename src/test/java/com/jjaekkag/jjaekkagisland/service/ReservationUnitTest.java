package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.domain.Lesson;
import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import com.jjaekkag.jjaekkagisland.domain.dto.EnrolledMemberResDto;
import com.jjaekkag.jjaekkagisland.repository.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author jeongheekim
 * @date 12/6/23
 */
@ExtendWith(MockitoExtension.class)
public class ReservationUnitTest {
    @Mock
    private ReservationRepository reservationRepository;


    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("매장별, 수업별 예약 이력 현황 조회 성공 테스트")
    void successTest() {
        Long lessonSeq = 1L;
        Member member = Mockito.mock(Member.class);
        Lesson lesson = Mockito.mock(Lesson.class);

        Reservation reservation = Reservation.builder()
                .member(member)
                .lesson(lesson)
                .participant(2)
                .build();
        when(reservationRepository.findEnrolledMemberListByLessonSeq(lessonSeq))
                .thenReturn(Arrays.asList(reservation));

        List<EnrolledMemberResDto> enrolledMemberList = reservationService.getEnrolledMemberList(lessonSeq);

        assertEquals(1, enrolledMemberList.size());
    }
}
