/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.services;

import com.cua.admin.model.entities.User;
import com.cua.admin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 *
 * @author esteban_santiago
 */

@Service
public class UserService {
        @Autowired
	private UserRepository userRepository;
        
        public void lock(Integer id) {
            this.lock(userRepository.findById(id));
        }

        public void unlock(Integer id) {
            this.unlock(userRepository.findById(id));
        }


        public void lock(User user) {
            user.setLocked(Boolean.TRUE);
            userRepository.save(user);
        }
        
        public void unlock(User user) {
            user.setLocked(Boolean.FALSE);
            userRepository.save(user);
        }
} 
