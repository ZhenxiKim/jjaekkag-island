package com.jjaekkag.jjaekkagisland.service;

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
            lockRepository.getLock(lessonSeqVal);
            reservationSeq = reservationService.reservation(dto, memberSeq);
        } catch (Exception e) {
            log.error("Reservation Fail : {}", e.getMessage());
        } finally {
            lockRepository.releaseLock(String.valueOf(lessonSeqVal));
        }
        return reservationSeq;
    }
}
