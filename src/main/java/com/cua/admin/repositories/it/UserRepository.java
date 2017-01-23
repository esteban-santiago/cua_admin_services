package com.cua.admin.repositories.it;

import com.cua.admin.model.it.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    User findById(Integer id);
   

}
