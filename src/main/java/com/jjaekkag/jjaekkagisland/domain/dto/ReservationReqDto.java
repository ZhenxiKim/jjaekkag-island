package com.jjaekkag.jjaekkagisland.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

/**
 * @author jeongheekim
 * @date 12/3/23
 */

public record ReservationReqDto(@NotNull Long storeSeq, @NotNull Long lessonSeq,
                                @NotNull @Max(value = 5, message = "한 수업에 예매 가능한 인원 수를 초과했습니다.") Integer participant) {

}
