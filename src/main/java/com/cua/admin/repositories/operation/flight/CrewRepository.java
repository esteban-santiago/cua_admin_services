package com.cua.admin.repositories.operation.flight;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.operation.flight.CrewMember;
import com.cua.admin.model.operation.flight.CrewMemberRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<CrewMember, Integer> {

    Optional<List<CrewMember>> findByCrewMemberRole(CrewMemberRole crewMemeberRole);
    Optional<CrewMember> findByPerson(Person person);
    Optional<CrewMember> findByPersonAndCrewMemberRole(Person person, CrewMemberRole crewMemberRole);
  
}
