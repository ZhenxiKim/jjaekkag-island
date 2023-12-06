package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findFirstByOrderByMemberSeqDesc();

}
