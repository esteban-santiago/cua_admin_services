package com.cua.admin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author esteban_santiago
 */
@Configuration
@EnableWebMvc
public class CorsConfiguration extends WebMvcConfigurerAdapter {
     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOrigins("http://localhost:8383");
                 //.allowedOrigins("http://localhost:80");
     }
}