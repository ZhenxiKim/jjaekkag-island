package com.jjaekkag.jjaekkagisland.common.exception;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public class AlreadyBookedException extends RuntimeException {
    public AlreadyBookedException(String message) {
        super(message);
    }
}
