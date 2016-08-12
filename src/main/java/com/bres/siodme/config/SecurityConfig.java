//package com.bres.siodme.config;
//
///**
// * Created by Adam on 2016-08-02.
// */
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackageClasses = com.bres.siodme.web.security.UserDetailsServiceImpl.class)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
//    }
//
//    @Bean(name="authenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/registration").access("hasRole('ROLE_USER')")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin().loginPage("/loginView")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/loginView?logout")
//                .and()
//                .exceptionHandling().accessDeniedPage("/errorView")
//                .and()
//                .csrf();
//    }
//
//    @Bean(name="bCryptPasswordEncoder")
//    public BCryptPasswordEncoder passwordencoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
