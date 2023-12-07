package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.common.exception.*;
import com.jjaekkag.jjaekkagisland.domain.Lesson;
import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import com.jjaekkag.jjaekkagisland.domain.ReservationStatus;
import com.jjaekkag.jjaekkagisland.domain.dto.EnrolledMemberResDto;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationCommonResDto;
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

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ReservationService implements ReservationCommon<ReservationReqDto> {

    private static final long AVAIL_BEFORE_DAYS = 14;
    private static final long CANCEL_AVAIL_HOUR = 1;
    private final ReservationRepository reservationRepository;

    private final LessonService lessonService;
    private final MemberService memberService;

    @Transactional
    public ReservationCommonResDto reservation(ReservationReqDto dto, Long memberSeq) {
        Lesson lesson = lessonService.getLesson(dto.lessonSeq());
        checkOverLessonDate(lesson);
        checkOverLimitParticipant(dto);
        checkAlreadyBookedLesson(dto, memberSeq);
        return new ReservationCommonResDto(createReservation(memberSeq, lesson, dto));
    }

    private void checkOverLessonDate(Lesson lesson) {
        LocalDateTime lessonDateTime = LocalDateTime.of(lesson.getStartDate(), lesson.getStartTime());
        long between = Math.abs(ChronoUnit.DAYS.between(lessonDateTime, LocalDateTime.now()));
        if (between > AVAIL_BEFORE_DAYS) {
            throw new ReservationNotYetAvailableException("아직 예매 가능한 날짜가 아닙니다.");
        }
    }

    private void checkAlreadyBookedLesson(ReservationReqDto dto, Long memberSeq) {
        Member member = memberService.getMember(memberSeq);
        Lesson lesson = lessonService.getLesson(dto.lessonSeq());
        reservationRepository.findReservationByLessonAndMember(lesson, member)
                .ifPresent(reservation -> {
                    log.error("이미 예약된 내역이 있습니다.");
                    throw new AlreadyBookedException("이미 예약된 내역이 있습니다.");
                });
    }


    private void checkOverLimitParticipant(ReservationReqDto dto) {
        Lesson lesson = lessonService.getLesson(dto.lessonSeq());
        List<Reservation> reservationList = reservationRepository.findReservationByLessonAndReservationStatus(lesson, ReservationStatus.RESERVATION);
        int limitParticipant = lesson.getLimitParticipant();
        boolean isOver = reservationList.size() + dto.participant() >= limitParticipant;
        if (isOver) {
            log.error("신청 가능한 인원({})을 초과하였습니다.", limitParticipant);
            throw new ParticipantLimitOverException("신청 가능한 인원을 초과하였습니다.");
        }
    }

    private Reservation createReservation(Long memberSeq, Lesson lesson, ReservationReqDto dto) {
        Member member = memberService.getMember(memberSeq);
        return reservationRepository.save(new Reservation(lesson, member, dto.participant()));
    }

    public ReservationResDto getReservation(Long reservationSeq) {
        return reservationRepository.findById(reservationSeq)
                .stream().map(ReservationResDto::new).findFirst().orElseThrow(EntityNotFoundException::new);
    }


    public ReservationCommonResDto cancelLesson(Long reservationSeq, Long memberSeq) {
        Reservation reservation = reservationRepository.findById(reservationSeq).orElseThrow(DataNotFoundException::new);
        Lesson lesson = reservation.getLesson();
        checkAvailCancelLesson(lesson);
        reservation.updateResultStatus(ReservationStatus.CANCEL);
        reservation.updateCancelDateTime();
        return new ReservationCommonResDto(reservationRepository.save(reservation));
    }

    private static void checkAvailCancelLesson(Lesson lesson) {
        Duration duration = Duration.between(lesson.getStartTime(), LocalDateTime.now()).abs();
        long hours = duration.toHours();
        if (hours < CANCEL_AVAIL_HOUR) {
            String errMsg = CANCEL_AVAIL_HOUR + "시간 전에는 수업 취소가 불가합니다.";
            log.error(errMsg);
            throw new LessonCancelNotAvailException(errMsg);
        }
    }

    @Transactional(readOnly = true)
    public List<EnrolledMemberResDto> getEnrolledMemberList(Long lessonSeq) {
        Lesson lesson = lessonService.getLesson(lessonSeq);
        return reservationRepository.findReservationByLessonAndReservationStatus(lesson, ReservationStatus.RESERVATION)
                .stream().map(EnrolledMemberResDto::new)
                .toList();
    }
}
