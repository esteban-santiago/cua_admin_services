package com.cua.admin.repositories.core;

import com.cua.admin.model.core.Nationality;
import com.cua.admin.model.core.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);

    List<Person> findByNationality(Nationality nationality);

    Optional<Person> findById(Integer id);
    
    //Socios
    Optional<Person> findByIdAndMemberProfileIsNotNull(Integer id);
    List<Person> findByMemberProfileIsNotNull();
    //Pilotos
    Optional<Person> findByIdAndPilotProfileIsNotNull(Integer id);
    List<Person> findByPilotProfileIsNotNull();
    //Clientes
    Optional<Person> findByIdAndCustomerProfileIsNotNull(Integer id);
    List<Person> findByCustomerProfileIsNotNull();
    //Empleados
    Optional<Person> findByIdAndEmployeeProfileIsNotNull(Integer id);
    List<Person> findByEmployeeProfileIsNotNull();

}
