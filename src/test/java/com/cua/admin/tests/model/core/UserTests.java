package com.cua.admin.tests.model.core;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.core.UserRepository;
import com.cua.admin.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests extends SpringIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        User user = new User();
        //user.setId(0);
        user.setName("Esteban");
        user.setPasswd("passwd");
        userRepository.save(user);

        user.lock();
        
        assertThat(user.getId()).isGreaterThan(0);

        
        userRepository.findAll().forEach((u) -> System.out.println(u.toString()));

        userRepository.findByPasswd("password2")
                .forEach((u) -> System.out.println(u.toString()));
    }
}
