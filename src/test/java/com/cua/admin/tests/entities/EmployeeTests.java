package com.cua.admin.tests.entities;

import com.cua.admin.entities.Address;
import com.cua.admin.entities.Employee;
import com.cua.admin.entities.EmployeeActivity;
import com.cua.admin.entities.Person;
import com.cua.admin.entities.PersonCategory;
import com.cua.admin.repositories.EmployeeRepository;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest


public class EmployeeTests {
    @Autowired
    private EmployeeRepository employeeService;

    
    @Test
    public void createEmployee() {
        EmployeeActivity activity = new EmployeeActivity(3, "Mecanico");
        PersonCategory category = new PersonCategory(2, "Empleado");

        Address address = new Address();
        address.setStreet("Cramer 2222");
        address.setCity("Ciudad Autónoma de Buenos Aires");
        address.setZip("1418");
        
        Employee mecanico = new Employee();
        mecanico.setName("Mecanico 1");
        mecanico.setActivity(activity);
        mecanico.setCategory(category);
        mecanico.setDateOfCreation(LocalDate.now());
        mecanico.addAddress(address);
        employeeService.save(mecanico);
        
        
        Employee mecanico2 = new Employee();
        mecanico2.setName("Mecanico 2");
        mecanico2.setActivity(activity);
        mecanico2.setCategory(category);
        employeeService.save(mecanico2);
        
        //System.out.println(category);
        
        for(Employee e : employeeService.findAll()) {
            System.out.println(e.toString() + " : " + e.getActivity().toString());
        }
    }
}
