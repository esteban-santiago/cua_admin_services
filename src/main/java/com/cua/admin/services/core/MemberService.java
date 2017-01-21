package com.cua.admin.services.core;

import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.exceptions.MemberNotFoundException;
import com.cua.admin.repositories.core.MemberRepository;
import java.util.List;
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
@Transactional
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    
    public Member get(Integer id) throws Throwable {
        return memberRepository.findById(id).orElseThrow(
        () -> new MemberNotFoundException(id));
    }
    
    public Member get(Member member) throws Throwable {
        return this.get(member.getId());
    }
    
    public void save(Member member) {
        this.memberRepository.save(member);
    }

    public List<Member> getAll() {
        return this.memberRepository.findAll();
    }
}
