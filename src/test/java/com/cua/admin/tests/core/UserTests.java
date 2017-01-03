package com.cua.admin.tests.core;

import com.cua.admin.model.core.User;
import com.cua.admin.model.core.repositories.UserRepository;
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

        assertThat(user.getId()).isGreaterThan(0);
        assertThat(user2.getId()).isGreaterThan(0);

        //user.setName("pepe");
        userRepository.findAll().forEach((u) -> System.out.println(u.toString()));

        userRepository.findByPasswd("password2")
                .forEach((u) -> System.out.println(u.toString()));
    }
}
