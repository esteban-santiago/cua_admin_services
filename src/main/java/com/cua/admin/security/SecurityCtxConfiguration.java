package com.cua.admin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityCtxConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/sapi/**").permitAll() //authenticated()
                .antMatchers(HttpMethod.POST, "/sapi/**").permitAll() //authenticated()
                .antMatchers(HttpMethod.PUT, "/sapi/**").permitAll() //authenticated()
                .antMatchers(HttpMethod.DELETE, "/sapi/**").permitAll() //authenticated()
                .antMatchers(HttpMethod.OPTIONS,"/sapi/**").permitAll() //authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    

}
