/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 *
 * @author esteban_santiago
 */
@Configuration
@EnableWebSecurity
public class SecurityCtxConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            //.antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers(HttpMethod.GET, "/sapi/**").authenticated()
            .antMatchers(HttpMethod.POST, "/sapi/**").authenticated()
            .antMatchers(HttpMethod.PUT, "/sapi/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/sapi/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .httpBasic().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
}
