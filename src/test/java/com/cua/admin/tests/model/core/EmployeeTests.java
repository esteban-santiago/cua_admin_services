package com.cua.admin.tests.model.core;

import com.cua.admin.model.core.*;
import com.cua.admin.repositories.core.ActivityRepository;
import com.cua.admin.repositories.core.CategoryRepository;
import com.cua.admin.repositories.core.EmployeeRepository;
import com.cua.admin.repositories.core.NationalityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class EmployeeTests extends SpringIntegrationTest {

    @Autowired
    private EmployeeRepository employeeService;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CategoryRepository categoryReposity;

    @Autowired
    private NationalityRepository nationalityReposity;



    @Test
    public void createEmployee() {
        Nationality peruvian = new Nationality("Peruana");
        nationalityReposity.save(peruvian);
        
        Activity activity = new Activity("Mecanico");
        activityRepository.save(activity);
        
        Category category = new Category("Empleado");
        categoryReposity.save(category);

        Address address = new Address();
        address.setStreet("Cramer 2222");
        address.setCity("Ciudad Autónoma de Buenos Aires");
        address.setZip("1418");
        address.setProvince("Ciudad Autónoma de Buenos Aires");
        address.setCountry("Argentina");

        ContactWay mail = new ContactWay("M", "esteban.s@gmail.com");
        ContactWay mail2 = new ContactWay("M", "esteban.san@gmail.com");

        Employee mecanico = new Employee();
        mecanico.setName("Mecanico 1");
        mecanico.setActivity(activity);
        mecanico.setCategory(category);
        mecanico.setDateOfCreation(LocalDate.now());
        mecanico.setDateOfBirth(LocalDate.parse("1974-08-02"));
        mecanico.addAddress(address);
        mecanico.addWayToContact(mail);
        mecanico.addWayToContact(mail2);
        mecanico.setNationality(peruvian);
        employeeService.save(mecanico);

        Employee mecanico2 = new Employee();
        mecanico2.setName("Mecanico 2");
        mecanico2.setActivity(activity);
        mecanico2.setCategory(category);
        mecanico2.setDateOfCreation(LocalDate.now().plusDays(10));
        mecanico2.setDateOfBirth(LocalDate.parse("1976-08-30"));
        mecanico2.setNationality(peruvian);
        employeeService.save(mecanico2);

        employeeService.findAll().stream().forEach((e) -> {
            System.out.println(e + " : " + e.getActivity());
        });
    }

    @Test
    public void getEmployeesByNationality() {
        Nationality peruvian = nationalityReposity.findById(2);
        employeeService.findByNationality(peruvian).forEach(System.out::println);
    }
}