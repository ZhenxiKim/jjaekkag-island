package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.common.exception.ReservationFailException;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationCommonResDto;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationReqDto;
import com.jjaekkag.jjaekkagisland.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final LockRepository lockRepository;
    private final ReservationService reservationService;

    public Long reservation(ReservationReqDto dto, Long memberSeq) {
        String lessonSeqVal = String.valueOf(dto.lessonSeq());
        Long reservationSeq = null;
        try {
            log.info("Get Lock Success!! : {}", lessonSeqVal);
            lockRepository.getLock(lessonSeqVal);
            ReservationCommonResDto reservation = reservationService.reservation(dto, memberSeq);
            reservationSeq = reservation.getReservationSeq();
        } catch (RuntimeException e) {
            log.error("Reservation Fail : {}", e.getMessage());
            throw new ReservationFailException(e);
        } finally {
            lockRepository.releaseLock(String.valueOf(lessonSeqVal));
            log.info("Release Lock Success!! : {}", lessonSeqVal);
        }
        return reservationSeq;
    }
}
