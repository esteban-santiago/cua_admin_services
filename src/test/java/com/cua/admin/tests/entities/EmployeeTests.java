package com.cua.admin.tests.entities;

import com.cua.admin.model.Address;
import com.cua.admin.model.Employee;
import com.cua.admin.model.Activity;
import com.cua.admin.model.Category;
import com.cua.admin.repositories.EmployeeRepository;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import com.cua.admin.repositories.ActivityRepository;
import com.cua.admin.repositories.CategoryRepository;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@RunWith(SpringRunner.class)
@SpringBootTest


public class EmployeeTests {
    @Autowired
    private EmployeeRepository employeeService;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private CategoryRepository categoryReposity;
    
    @Test
    public void createEmployeeActivity() {
        Activity activity = new Activity("Mecanico");
        activityRepository.save(activity);
    }

    @Test
    public void createPersonCategory() {
        Category category = new Category("Empleado");
        categoryReposity.save(category);
    }
    
    @Test
    public void createEmployee() {
        Activity activity = activityRepository.findByDescription("Mecanico").get(0);
        Assert.notNull(activity);
        Category category = categoryReposity.findByDescription("Empleado").get(0);
        Assert.notNull(category);

        Address address = new Address();
        address.setStreet("Cramer 2222");
        address.setCity("Ciudad Autónoma de Buenos Aires");
        address.setZip("1418");
        address.setProvince("Ciudad Autónoma de Buenos Aires");
        address.setCountry("Argentina");
        
        
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
        mecanico2.setDateOfCreation(LocalDate.now().plus(10, ChronoUnit.DAYS));
        mecanico2.setCategory(category);
        employeeService.save(mecanico2);
        
        //System.out.println(category);
        
        for(Employee e : employeeService.findAll()) {
            System.out.println(e.toString() + " : " + e.getActivity().toString());
        }
    }
}
