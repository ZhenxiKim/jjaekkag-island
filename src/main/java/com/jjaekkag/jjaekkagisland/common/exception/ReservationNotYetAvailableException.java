package com.jjaekkag.jjaekkagisland.common.exception;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
public class ReservationNotYetAvailableException extends RuntimeException {
    public ReservationNotYetAvailableException(String message) {
        super(message);
    }
}
