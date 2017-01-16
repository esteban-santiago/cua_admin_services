package com.cua.admin.services;

import com.cua.admin.model.core.User;
import com.cua.admin.repositories.core.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void lock(Integer id) {
        this.lock(userRepository.findById(id));
    }

    public void unlock(Integer id) {
        this.unlock(userRepository.findById(id));
    }

    public void lock(User user) {
        user.lock();
        userRepository.save(user);
    }

    public void unlock(User user) {
        user.lock();
        userRepository.save(user);
    }

    public User get(User user) {
        return userRepository.findById(user.getId());
    }

    public User get(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


} 
