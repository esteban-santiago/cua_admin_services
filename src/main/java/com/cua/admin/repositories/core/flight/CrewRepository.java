package com.cua.admin.repositories.core.flight;

import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.flight.CrewMember;
import com.cua.admin.model.core.flight.CrewMemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<CrewMember, Integer> {

    List<CrewMember> findByCrewMemberRole(CrewMemberRole crewMemeberRole);
    CrewMember findByMember(Member member);
    CrewMember findByMemberAndCrewMemberRole(Member member, CrewMemberRole crewMemberRole);
  
}
