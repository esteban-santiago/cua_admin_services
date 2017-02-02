package com.cua.admin.repositories.hr;

import com.cua.admin.model.hr.profiles.Employee;
import com.cua.admin.model.core.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    List<Employee> findByNationality(Nationality nationality);

}
