package com.cua.admin.tests.entities;

import com.cua.admin.model.Address;
import com.cua.admin.model.Person;
import com.cua.admin.model.PersonCategory;
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
        Address address = new Address();
        address.setStreet("Av. Berlin 729");
        address.setCity("Longchamps");
        address.setZip("1854");
        
        Address address2 = new Address();
        address2.setStreet("Pasaje Bélgica 837");
        address2.setCity("Adrogué");
        address2.setZip("1846");
        
        
        
        
        
        PersonCategory category = new PersonCategory(1, "Socio");
        PersonCategory category2 = new PersonCategory(2, "Empleado");
        
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
        
        //System.out.println(category); 
    }

    @Test
    public void findPerson() {
        for(Person p : personService.findAll()) {
            Assert.notNull(p.getAddress());
            System.out.println(p.toString() + " : " + p.getCategory().toString());
        }    
    }
}
