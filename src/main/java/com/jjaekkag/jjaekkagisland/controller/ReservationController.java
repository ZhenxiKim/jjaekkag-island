package com.jjaekkag.jjaekkagisland.controller;

import com.jjaekkag.jjaekkagisland.domain.dto.ReservationReqDto;
import com.jjaekkag.jjaekkagisland.service.ReservationFacade;
import com.jjaekkag.jjaekkagisland.service.ReservationService;
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

    @PostMapping
    public ResponseEntity<Object> reservation(@RequestBody @Valid ReservationReqDto dto, Long memberSeq) {
        Long reservationSeq = reservationFacade.reservation(dto, memberSeq);
        if (ObjectUtils.isEmpty(reservationSeq)) throw new IllegalArgumentException();
        return ResponseEntity.created(URI.create(INDEX_URI))
                .body(reservationService.getReservation(reservationSeq));
    }

    @PutMapping(path = "/{reservationSeq}")
    public ResponseEntity<?> cancel(@PathVariable @NotNull Long reservationSeq) {
        reservationService.cancelLesson(reservationSeq);
        return ResponseEntity.ok().build();
    }

}
