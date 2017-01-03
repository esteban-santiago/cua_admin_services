package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findByPasswd(String passwd);

    User findById(Integer id);
}
