package com.cua.admin.services.it;

import com.cua.admin.model.it.User;
import com.cua.admin.repositories.it.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    
    @Autowired //No es obligatorio
    private final UserRepository userRepository;

    public void lock(Integer id) {
        this.lock(userRepository.findById(id).get());
    }

    public void unlock(Integer id) {
        this.unlock(userRepository.findById(id).get());
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
        return userRepository.findById(id).get();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public Page<User> getAllByPage(Integer number, Integer size) {
        PageRequest request =
            new PageRequest(number - 1, size, Sort.Direction.DESC, "name");
        return userRepository.findAll(request);
    }

} 
