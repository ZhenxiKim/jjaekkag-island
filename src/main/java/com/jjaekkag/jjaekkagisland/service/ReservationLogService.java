package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.common.exception.DataNotFoundException;
import com.jjaekkag.jjaekkagisland.domain.Reservation;
import com.jjaekkag.jjaekkagisland.domain.ReservationLog;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationLogResDto;
import com.jjaekkag.jjaekkagisland.repository.LessonRepository;
import com.jjaekkag.jjaekkagisland.repository.ReservationLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jeongheekim
 * @date 12/5/23
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ReservationLogService {

    private final ReservationLogRepository reservationLogRepository;
    private final LessonRepository lessonRepository;

    public void insertLog(ReservationLog log) {
        reservationLogRepository.save(log);
    }

    @Transactional(readOnly = true)
    public List<ReservationLogResDto> getLog(Long lessonSeq) {
        List<Reservation> reservations = lessonRepository.findById(lessonSeq)
                .orElseThrow(DataNotFoundException::new).getReservations();
        return reservations.stream().map(ReservationLogResDto::new).collect(Collectors.toList());
    }
}
