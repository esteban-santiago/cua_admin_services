/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.rest;

import com.cua.admin.model.core.User;
import com.cua.admin.model.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UnsecureRestService {

    private final UserRepository userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json")
    public User login(@RequestParam(value = "user", required = true) User user) {
        User pp = userService.findByName("Esteban").get(0);
        User p = new User("esteban", "santiago");
        System.out.println("Usuario: " + p.toString());
        return pp;
    }
}
