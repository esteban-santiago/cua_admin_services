package com.cua.admin.repositories.core;

import com.cua.admin.model.core.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    User findById(Integer id);

}
