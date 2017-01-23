package com.cua.admin.tests.model.core;

import com.cua.admin.model.it.User;
import com.cua.admin.repositories.it.UserRepository;
import com.cua.admin.services.core.UserService;
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
        user.setPassword("passwd");
        userRepository.save(user);

        user.lock();
        
        assertThat(user.getId()).isGreaterThan(0);
        
        userRepository.findAll().forEach((u) -> System.out.println(u.toString()));

    }
}
