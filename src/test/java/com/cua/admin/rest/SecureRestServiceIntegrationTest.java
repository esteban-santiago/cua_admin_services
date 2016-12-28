package com.cua.admin.rest;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.UserRepository;
import com.cua.admin.tests.core.SpringIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Este es un ejemplo de un Integration Test muy simple que integra un controller,
 * seguridad y búsqueda de un usuario
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecureRestServiceIntegrationTest extends SpringIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("Esteban", "pass");
        user = userRepository.saveAndFlush(user);
    }

    @After
    public void tearDown() throws Exception {
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(get("/sapi/user?id={userId}", user.getId()).with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Esteban"));
    }

}