package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.domain.dto.ReservationCommmonResDto;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
public interface ReservationCommon<T> {
    ReservationCommmonResDto reservation(T dto, Long memberSeq);
    ReservationCommmonResDto cancelLesson(Long seq,Long memberSeq);
}
