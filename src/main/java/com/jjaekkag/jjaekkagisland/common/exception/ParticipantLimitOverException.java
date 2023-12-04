package com.jjaekkag.jjaekkagisland.common.exception;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public class ParticipantLimitOverException extends RuntimeException{
    public ParticipantLimitOverException(String message) {
        super(message);
    }
}
