package com.jjaekkag.jjaekkagisland.repository;

import com.jjaekkag.jjaekkagisland.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberSeq(Long memberSeq);

}
