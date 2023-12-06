package com.jjaekkag.jjaekkagisland.common.exception;

/**
 * @author jeongheekim
 * @date 12/6/23
 */
public class ReservationFailException extends RuntimeException {
    public ReservationFailException(RuntimeException e) {
        super(e);
    }
}
