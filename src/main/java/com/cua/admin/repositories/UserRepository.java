/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.entities.User;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);
    List<User> findByPasswd(String passwd);
    User findById(Integer id);
}
