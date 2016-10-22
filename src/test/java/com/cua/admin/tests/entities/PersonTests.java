package com.cua.admin.tests.entities;

import com.cua.admin.model.Address;
import com.cua.admin.model.Person;
import com.cua.admin.model.Category;
import com.cua.admin.repositories.EmployeeActivityRepository;
import com.cua.admin.repositories.PersonCategoryRepository;
import com.cua.admin.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest


public class PersonTests {
    @Autowired
    private PersonRepository personService;
    @Autowired
    private EmployeeActivityRepository employeeActivityRepository;
    @Autowired
    private PersonCategoryRepository personCategoryReposity;
    

    @Test
    public void createPersonCategory() {
        Category category = new Category("Socio");
        personCategoryReposity.save(category);
        Category category2 = new Category("Empleado");
        personCategoryReposity.save(category2);
    }

    
    @Test
    public void createPerson() {
        Address address = new Address();
        address.setStreet("Av. Berlin 729");
        address.setCity("Longchamps");
        address.setZip("1854");
        
        Address address2 = new Address();
        address2.setStreet("Pasaje Bélgica 837");
        address2.setCity("Adrogué");
        address2.setZip("1846");
        
        Category category = personCategoryReposity.findByDescription("Socio").get(0);
        //System.out.println("Category:" + category);
        //PersonCategory category2 = personCategoryReposity.findByDescription("Empleado").get(0);
        
        Person member = new Person();
        member.setName("Socio 1");
        member.setCategory(category);
        member.addAddress(address);
        member.addAddress(address2);
        personService.save(member);
                
        Person member2 = new Person();
        member2.setName("Socio 2");
        member2.setCategory(category);
        personService.save(member2);
        

        for(Person p : personService.findAll()) {
            Assert.notNull(p.getAddress());
            System.out.println(p.toString());
        }    
    }
}
