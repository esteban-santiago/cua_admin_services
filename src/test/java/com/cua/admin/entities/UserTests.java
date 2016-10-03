package com.cua.admin.entities;

import com.cua.admin.entities.User;
import com.cua.admin.repositories.UserRepository;
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
        user.setId(0);
        user.setName("Esteban");
        user.setPasswd("passwd");
        userService.save(user);
        User user2 = userService.findById(0);
        System.out.println(user2.toString());
            
    }

}
