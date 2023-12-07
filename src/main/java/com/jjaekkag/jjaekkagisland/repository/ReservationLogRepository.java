package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.ReservationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jeongheekim
 * @date 12/4/23
 */
public interface ReservationLogRepository extends JpaRepository<ReservationLog,Long> {
}
