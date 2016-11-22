/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.core.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author esteban_santiago
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findByPasswd(String passwd);

    User findById(Integer id);
}
