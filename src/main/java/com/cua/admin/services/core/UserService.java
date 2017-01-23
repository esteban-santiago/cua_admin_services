package com.cua.admin.services.core;

import com.cua.admin.model.it.User;
import com.cua.admin.repositories.it.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    @Autowired //No es obligatorio
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
        user.unLock();
        userRepository.save(user);
    }

    public User get(User user) {
        return this.get(user.getId());
    }

    public User get(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
} 
