package com.cua.admin.tests.entities;

import com.cua.admin.entities.Person;
import com.cua.admin.entities.PersonCategory;
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

    
    @Test
    public void createPerson() {
        PersonCategory category = new PersonCategory("Socio");
        PersonCategory category2 = new PersonCategory("Empleado");
        
        Person member = new Person();
        member.setName("Socio 1");
        member.setCategory(category);
        personService.save(member);
                
        Person member2 = new Person();
        member2.setName("Socio 2");
        member2.setCategory(category2);
        personService.save(member2);
        
        //System.out.println(category);
        
        for(Person p : personService.findAll()) {
            System.out.println(p.toString() + " : " + p.getCategory().toString());
        }
    }
}
