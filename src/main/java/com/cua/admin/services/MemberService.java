package com.cua.admin.services;

import com.cua.admin.model.core.Member;
import com.cua.admin.repositories.core.MemberRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author esantiago
 */
@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    
    public Member get(Integer id) {
        return memberRepository.findById(id);
    }
    
    public Member get(Member member) {
        return this.get(member.getId());
    }
    
}
