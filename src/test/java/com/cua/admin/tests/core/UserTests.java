package com.cua.admin.tests.core;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.UserRepository;
import com.cua.admin.services.UserService;
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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void lockUser() {
        User user = userRepository.findByName("Esteban").get(0);
        System.out.println("-----Bloqueo de usuario----");
        userService.lock(user);
    }

    @Test
    public void createUser() {
        User user = new User();
        //user.setId(0);
        user.setName("Esteban");
        user.setPasswd("passwd");
        userRepository.save(user);

        User user2 = new User("esteban2", "password2");
        userRepository.save(user2);

        Assert.assertTrue("Correcto", user.getId() > 0);
        Assert.assertTrue("Correcto", user2.getId() > 0);

        //user.setName("pepe");
        userRepository.findAll().stream().forEach((u) -> {
            System.out.println(u.toString());
        });

        userRepository.findByPasswd("password2").stream().forEach((u) -> {
            System.out.println(u.toString());
        });
    }
}