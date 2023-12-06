package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.domain.ReservationLog;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationLogResDto;
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

    public void insertLog(ReservationLog log) {
        reservationLogRepository.save(log);
    }

    @Transactional(readOnly = true)
    public List<ReservationLogResDto> getLog(Long lessonSeq) {
        return reservationLogRepository.findAllByLessonSeq(lessonSeq)
                .stream().map(ReservationLogResDto::new).collect(Collectors.toList());
    }
}
