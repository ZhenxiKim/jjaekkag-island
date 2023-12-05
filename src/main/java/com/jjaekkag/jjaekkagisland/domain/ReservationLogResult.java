package com.jjaekkag.jjaekkagisland.domain;

import java.time.LocalDateTime;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
public interface ReservationLogResult {
    String getEmail();

    String getName();

    String getLessonName();

    Integer getParticipant();

    String getReservationStatus();

    LocalDateTime getCreatedDate();

    LocalDateTime getUpdatedDate();
}
