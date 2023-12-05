package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.ReservationLog;
import com.jjaekkag.jjaekkagisland.domain.ReservationLogResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
public interface ReservationLogRepository extends JpaRepository<ReservationLog,Long> {
    @Query(value = "select m.email, m.name, l.name as lessonName, r.participant, r.reservation_status, r.created_date_time, r.updated_date_time " +
            "from reservation_log rl " +
            "         left join lesson l on l.lesson_seq = rl.lesson_seq " +
            "         left join reservation r on r.reservation_seq = rl.reservation_seq " +
            "         left join member m on m.member_seq = rl.member_seq " +
            "where l.lesson_seq = :lessonSeq ", nativeQuery = true)
    List<ReservationLogResult> findAllByLessonSeq(@Param(value = "lessonSeq") Long lessonSeq);
}
