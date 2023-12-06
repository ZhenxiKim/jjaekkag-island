package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.domain.dto.ReservationCommonResDto;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
public interface ReservationCommon<T> {
    ReservationCommonResDto reservation(T dto, Long memberSeq);
    ReservationCommonResDto cancelLesson(Long seq, Long memberSeq);
}
