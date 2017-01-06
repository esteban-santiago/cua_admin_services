package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.Employee;
import com.cua.admin.model.core.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    List<Employee> findByNationality(Nationality nationality);

    Employee findById(Integer id);
}
