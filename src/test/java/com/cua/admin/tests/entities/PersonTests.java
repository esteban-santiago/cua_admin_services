package com.cua.admin.tests.entities;

import com.cua.admin.entities.Person;
import com.cua.admin.entities.PersonCategory;
import com.cua.admin.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest


public class PersonTests {
    @Autowired
    private PersonRepository personService;

    @Test
    public void createPerson() {
        Person member = new Person();
        member.setName("Socio 1");
        member.setCategory(new PersonCategory("Socio"));
        personService.save(member);
        
        //System.out.println(member.toString());
        
        for(Person p : personService.findAll())
            System.out.println(p.toString());
    }
}
