/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 *
 * @author esteban_santiago
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityCtxConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
     http.antMatcher("/api/*")
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/*")
        .permitAll();
    }
    
}
