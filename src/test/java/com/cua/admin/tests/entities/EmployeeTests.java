package com.cua.admin.tests.entities;

import com.cua.admin.entities.Employee;
import com.cua.admin.entities.EmployeeActivity;
import com.cua.admin.entities.Person;
import com.cua.admin.entities.PersonCategory;
import com.cua.admin.repositories.EmployeeRepository;
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
        EmployeeActivity activity = new EmployeeActivity("Mecanico");
        EmployeeActivity activity2 = new EmployeeActivity("Administrativo");
        
        Employee mecanico = new Employee();
        mecanico.setName("Mecanico 1");
        mecanico.setActivity(activity);
        employeeService.save(mecanico);
        
/*        
        Person member2 = new Person();
        member2.setName("Socio 2");
        member2.setCategory(category2);
        personService.save(member2);
        */
        //System.out.println(category);
        
        for(Employee e : employeeService.findAll()) {
            System.out.println(e.toString() + " : " + e.getActivity().toString());
        }
    }
}
