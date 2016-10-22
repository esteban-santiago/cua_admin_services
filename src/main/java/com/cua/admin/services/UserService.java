/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.services;

import com.cua.admin.model.User;
import com.cua.admin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author esteban_santiago
 */

public class UserService {
	@Autowired
	private UserRepository userService;
        
	public User getUser(Integer id) {
                System.out.println("Llamado el metodo!!:  " + id);
		User pp = userService.findByName("Esteban").get(0);
                //System.out.println("toString: " + userService.toString());
                User p = new User("esteban","santiago");
                p.setId(id);
                System.out.println("Usuario: " + p.toString());
		return pp;
	}
} 
