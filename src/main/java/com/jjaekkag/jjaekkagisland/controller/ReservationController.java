package com.jjaekkag.jjaekkagisland.controller;

import com.jjaekkag.jjaekkagisland.domain.dto.ReservationReqDto;
import com.jjaekkag.jjaekkagisland.service.ReservationFacade;
import com.jjaekkag.jjaekkagisland.service.ReservationLogService;
import com.jjaekkag.jjaekkagisland.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.jjaekkag.jjaekkagisland.controller.IndexController.INDEX_URI;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationFacade reservationFacade;
    private final ReservationLogService reservationLogService;

    @Operation(summary = "매장별, 수업별 예약 API", description = "로그인 후 token 획득 후 예약 가능")
    @PostMapping
    public ResponseEntity<Object> reservation(@RequestBody @Valid ReservationReqDto dto, Long memberSeq) {
        Long reservationSeq = reservationFacade.reservation(dto, memberSeq);
        if (ObjectUtils.isEmpty(reservationSeq)) throw new IllegalArgumentException();
        return ResponseEntity.created(URI.create(INDEX_URI))
                .body(reservationService.getReservation(reservationSeq));
    }

    @Operation(summary = "매장별, 수업별 예약 취소 API", description = "로그인 후 token 획득 후 취소 가능")
    @PutMapping(path = "/{reservationSeq}")
    public ResponseEntity<?> cancel(@PathVariable @NotNull Long reservationSeq,Long memberSeq) {
        reservationService.cancelLesson(reservationSeq, memberSeq);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{lessonSeq}")
    @Operation(summary = "매장별, 수업별 예약자 현황 API", description = "admin 계정 로그인 후 예약자 현황 조회 가능")
    public ResponseEntity<?> getEnrolledMember(@PathVariable @NotNull Long lessonSeq) {
        return ResponseEntity.ok(reservationService.getEnrolledMemberList(lessonSeq));
    }

    @Operation(summary = "매장별, 수업별 예약 이력 현황 API", description = "로그인 후 token 획득 후 취소 가능")
    @GetMapping(path = "/history/{lessonSeq}")
    public ResponseEntity<?> getReservationHistory(@PathVariable @NotNull Long lessonSeq) {
        return ResponseEntity.ok(reservationLogService.getLog(lessonSeq));
    }
}
