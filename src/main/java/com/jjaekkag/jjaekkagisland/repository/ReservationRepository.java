package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.Reservation;
import com.jjaekkag.jjaekkagisland.domain.ReservationResult;
import com.jjaekkag.jjaekkagisland.domain.ReservationStatus;
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

    @Query("SELECT r FROM Reservation r " +
            "join fetch r.member m " +
            "where m.memberSeq = :memberSeq " +
            "and r.lesson.lessonSeq = :lessonSeq")
    Optional<Reservation> findAllByLessonSeqAndMemberSeq(@Param("lessonSeq") Long lessonSeq, @Param("memberSeq") Long memberSeq);

    @Query("select r from Reservation r " +
            "where r.lesson.lessonSeq = :lessonSeq " +
            "and r.reservationStatus = :reservationStatus ")
    List<Reservation> findReservationLessonsByLessonSeq(@Param("lessonSeq") Long lessonSeq, @Param("reservationStatus") ReservationStatus reservationStatus);

    @Query(value = "SELECT s.name as storeName, s.address, l.name, r.participant " +
            "FROM store s " +
            "JOIN lesson l ON s.store_seq = l.store_seq " +
            "JOIN reservation r ON l.lesson_seq = r.lesson_seq " +
            "WHERE r.reservation_seq = :reservationSeq ", nativeQuery = true)
    Optional<ReservationResult> findReservationByReservationSeq(@Param("reservationSeq") Long reservationSeq);

    @Query(value = "SELECT r FROM Reservation r " +
            "join fetch r.member m " +
            "where r.lesson.lessonSeq = :lessonSeq " +
            "and r.cancelYn = false " +
            "order by r.createdDateTime ")
    List<Reservation> findEnrolledMemberListByLessonSeq(@Param("lessonSeq") Long lessonSeq);

    @Query("select r from Reservation r " +
            "join fetch r.member m " +
            "where r.lesson.lessonSeq = :lessonSeq " +
            "order by r.updatedDateTime ")
    List<Reservation> findReservationLogByLessonSeq(Long lessonSeq);
}
