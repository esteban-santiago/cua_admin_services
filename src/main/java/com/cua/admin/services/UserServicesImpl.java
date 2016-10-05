/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.services;

import com.cua.admin.entities.User;
import com.cua.admin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author esteban_santiago
 */

@RestController
@RequestMapping("/data")
public class UserServicesImpl {
	@Autowired
	private UserRepository userService;
	@RequestMapping(value = "/user", method = RequestMethod.GET, headers="Accept=application/json")
	public User getUser(@RequestParam(value = "id",required = false, defaultValue = "100") Integer id) {
                System.out.println("Llamado el metodo!!:  " + id);
		User pp = userService.findByName("Esteban").get(0);
                //System.out.println("toString: " + userService.toString());
                User p = new User("esteban","santiago");
                p.setId(id);
                System.out.println("Usuario: " + p.toString());
		return pp;
	}
} 
