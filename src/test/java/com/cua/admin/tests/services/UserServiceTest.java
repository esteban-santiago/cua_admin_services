package com.cua.admin.tests.services;

import com.cua.admin.model.it.User;
import com.cua.admin.repositories.it.UserRepository;
import com.cua.admin.services.it.UserService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Este es un ejemplo de Unit Test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    private User user;
    private Integer id;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);

        id = 1234;
        user = new User();
        user.setId(1234);
    }

    @Test
    public void lockById() throws Exception {
        // Given
        when(userRepository.findById(id)).thenReturn(user);

        // When
        userService.lock(id);

        // Then
        assertThat(user.isLocked()).isTrue();
        verify(userRepository).save(user);
    }

    @Test
    public void unlock() throws Exception {
        // Given
        user.isLocked();
        when(userRepository.findById(id)).thenReturn(user);

        // When
        userService.unlock(id);

        // Then
        assertThat(user.isLocked()).isFalse();
        verify(userRepository).save(user);
    }

    @Test
    public void lockByUser() throws Exception {
        // When
        userService.lock(user);

        // Then
        assertThat(user.isLocked()).isTrue();
        verify(userRepository).save(user);
    }

    @Test
    public void unlockByUser() throws Exception {
        // When
        userService.unlock(user);

        // Then
        assertThat(user.isLocked()).isFalse();
        verify(userRepository).save(user);
    }
    
}