package com.jjaekkag.jjaekkagisland.service;

import com.jjaekkag.jjaekkagisland.domain.Member;
import com.jjaekkag.jjaekkagisland.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMember(Long memberSeq) {
        return memberRepository.findById(memberSeq).orElseThrow();
    }

}
