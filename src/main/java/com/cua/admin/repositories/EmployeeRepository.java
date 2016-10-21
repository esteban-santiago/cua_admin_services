/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.Employee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByName(String name);
    Employee findById(Integer id);
}
