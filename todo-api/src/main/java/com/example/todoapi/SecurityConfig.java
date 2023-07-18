package com.example.todoapi;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем CSRF для простоты примера
                .authorizeRequests()
                .antMatchers("/api/user").permitAll()
                .antMatchers("/api/user/{userId}").permitAll()
                .antMatchers("/api/user/login").permitAll()// Разрешить доступ к этому пути всем
                .antMatchers("/api/tasks/{userId}/{taskId}").permitAll()// Разрешить доступ к этому пути всем
                .antMatchers("/api/tasks/{userId}").permitAll()
                .antMatchers("/api/tasks").permitAll()
                .anyRequest().authenticated() // Требовать аутентификацию для всех остальных запросов
                .and()
                .formLogin().disable(); // Отключить форму входа
    }


}