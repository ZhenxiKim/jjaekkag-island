package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findReservationByLessonAndMember(Lesson lesson, Member member);

    List<Reservation> findReservationByLessonAndReservationStatus(Lesson lesson, ReservationStatus reservationStatus);

    @Query(value = "SELECT r FROM Reservation r " +
            "join fetch r.member m " +
            "where r.lesson.lessonSeq = :lessonSeq " +
            "and r.reservationStatus = 'RESERVATION' " +
            "order by r.createdDateTime ")
    List<Reservation> findEnrolledMemberListByLessonSeq(@Param("lessonSeq") Long lessonSeq);

}
