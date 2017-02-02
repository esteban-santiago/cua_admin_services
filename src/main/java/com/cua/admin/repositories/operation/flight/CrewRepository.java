package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.profiles.Member;
import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<CrewMember, Integer> {

    List<CrewMember> findByCrewMemberRole(CrewMemberRole crewMemeberRole);
    CrewMember findByPerson(Person person);
    CrewMember findByPersonAndCrewMemberRole(Person person, CrewMemberRole crewMemberRole);
  
}
