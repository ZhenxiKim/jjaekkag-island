package com.jjaekkag.jjaekkagisland.common.aop;

import com.jjaekkag.jjaekkagisland.domain.ReservationLog;
import com.jjaekkag.jjaekkagisland.domain.ReservationStatus;
import com.jjaekkag.jjaekkagisland.domain.dto.ReservationCommonResDto;
import com.jjaekkag.jjaekkagisland.service.ReservationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * @author jeongheekim
 * @date 12/4/23
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class AopComponent {
    private final ReservationLogService reservationLogService;

    @Pointcut("execution(* com.jjaekkag.jjaekkagisland.service.ReservationCommon.*(..))")
    private void cut() {
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String methodName = methodSignature.getMethod().getName();
        ReservationStatus status = ReservationStatus.RESERVATION;
        log.info("aop method name : {}", methodName);
        if (methodName.contains("cancel")) {
            status = ReservationStatus.CANCEL;
        }

        ReservationCommonResDto dto = (ReservationCommonResDto) returnObj;
        reservationLogService.insertLog(new ReservationLog(dto, status));
    }
}
