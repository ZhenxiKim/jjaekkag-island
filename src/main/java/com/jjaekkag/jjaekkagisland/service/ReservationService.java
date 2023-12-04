package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.common.exception.*;
import com.jjaekkag.jjaekkagisland.domain.Lesson;
import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import com.jjaekkag.jjaekkagisland.domain.ReservationStatus;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationReqDto;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationResDto;
import com.jjaekkag.jjaekkagisland.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ReservationService {

    private static final long AVAIL_BEFORE_DAYS = 14;
    private static final long CANCEL_AVAIL_HOUR = 1;
    private final ReservationRepository reservationRepository;
    private final LessonService lessonService;
    private final MemberService memberService;

    @Transactional
    public Long reservation(ReservationReqDto dto, Long memberSeq) {
        Lesson lesson = lessonService.getLesson(dto.lessonSeq());
        checkOverLimitParticipant(dto);
        checkAlreadyBookedLesson(dto, memberSeq);
        checkOverLessonDate(lesson);
        return createReservation(memberSeq, lesson, dto);
    }

    private void checkOverLessonDate(Lesson lesson) {
        long between = ChronoUnit.DAYS.between(lesson.getStartTime(), LocalDateTime.now());
        if (between > AVAIL_BEFORE_DAYS) {
            throw new ReservationNotYetAvailableException("아직 예매 가능한 날짜가 아닙니다.");
        }
    }

    private Long createReservation(Long memberSeq, Lesson lesson, ReservationReqDto dto) {
        Member member = memberService.getMember(memberSeq);
        Reservation reservation = reservationRepository.save(new Reservation(lesson, member, dto.participant()));
        return reservation.getReservationSeq();
    }

    private void checkAlreadyBookedLesson(ReservationReqDto dto, Long memberSeq) {
        Optional<Reservation> reservation = reservationRepository.findAllByLessonSeqAndMemberSeq(dto.lessonSeq(), memberSeq);
        if (reservation.isPresent()) {
            throw new AlreadyBookedException("이미 예약된 내역이 있습니다.");
        }
    }


    private void checkOverLimitParticipant(ReservationReqDto dto) {
        Lesson lesson = lessonService.getLesson(dto.lessonSeq());
        List<Reservation> reservationList = reservationRepository.findReservationLessonsByLessonSeq(dto.lessonSeq(), ReservationStatus.SUCCESS);
        boolean isOver = reservationList.size() + dto.participant() >= lesson.getLimitParticipant();
        if (isOver) {
            throw new ParticipantLimitOverException("신청 가능한 인원을 초과하였습니다.");
        }
    }

    public ReservationResDto getReservation(Long reservationSeq) {
        return reservationRepository.findReservationByReservationSeq(reservationSeq)
                .stream().map(ReservationResDto::new).findFirst().orElseThrow(EntityNotFoundException::new);
    }

    public void cancelLesson(Long reservationSeq) {
        Reservation reservation = reservationRepository.findById(reservationSeq).orElseThrow(DataNotFoundException::new);
        Duration duration = Duration.between(reservation.getLesson().getStartTime(), LocalDateTime.now()).abs();
        long hours = duration.toHours();
        if (hours < CANCEL_AVAIL_HOUR) {
            throw new LessonCancelNotAvailException(CANCEL_AVAIL_HOUR + "시간 전에는 수업 취소가 불가합니다.");
        }
        reservation.updateCancelYn(true);
        reservationRepository.save(reservation);
    }
}
