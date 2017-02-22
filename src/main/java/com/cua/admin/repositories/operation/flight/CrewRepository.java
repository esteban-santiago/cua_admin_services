package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<CrewMember, Integer> {

    List<CrewMember> findByCrewMemberRole(CrewMemberRole crewMemeberRole);
    CrewMember findByPerson(Person person);
    CrewMember findByPersonAndCrewMemberRole(Person person, CrewMemberRole crewMemberRole);
  
}
