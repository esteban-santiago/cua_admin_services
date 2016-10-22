package com.cua.admin.tests.entities;

import com.cua.admin.model.Person;
import com.cua.admin.model.Category;
import com.cua.admin.model.User;
import com.cua.admin.repositories.UserRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest


public class UserTests {
    @Autowired
    private UserRepository userService;

    @Test
    public void createUser() {
        User user = new User();
        //user.setId(0);
        user.setName("Esteban");
        user.setPasswd("passwd");
        userService.save(user);
        
        User user2 = new User("esteban2", "password2");
        userService.save(user2);
        
        Assert.assertTrue("Correcto", user.getId()>0);
        Assert.assertTrue("Correcto", user2.getId()>0);
        
        for (User u:userService.findAll()){
            System.out.println(u.toString());
        }

        user.setName("pepe");
        
        for (User u:userService.findAll()){
            System.out.println(u.toString());
        }

        for (User u:userService.findByPasswd("password2")){
            System.out.println(u.toString());
        }
    }

    @Test
    public void createPerson() {
        Person member = new Person();
        member.setCategory(new Category("Socio"));
        
    }
}
