package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**").access("IS_AUTHENTICATED_ANONYMOUSLY")
                    .antMatchers("/**").access("hasRole('ROLE_USER')")
                    .antMatchers("/login").permitAll()
                    .antMatchers("/js/**", "/css/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                   // .loginProcessingUrl("/j_spring_security_check")
                    .failureForwardUrl("/login?error=true")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/logout_sec")
                    .permitAll();

    }
}
