package com.bres.siodme.config;

/**
 * Created by Adam on 2016-08-02.
 */
import com.bres.siodme.web.security.MySimpleUrlAuthenticationSuccessHandler;
import com.bres.siodme.web.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = com.bres.siodme.web.security.UserDetailsServiceImpl.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UserDetailsServiceImpl userDetailsServiceImpl;

    //@Autowired private UserDetailsService userDetailsService;

    @Autowired public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordencoder());
    }

    @Bean(name="authenticationManager")
    @Override public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name="bCryptPasswordEncoder")
    public BCryptPasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name="mySimpleUrlAuthenticationSuccessHandler")
    public MySimpleUrlAuthenticationSuccessHandler successHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                    .antMatchers("/registration").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("username").passwordParameter("password")
//                    .successHandler(successHandler())
//                    .failureUrl("/login?error")
//                    .and()
//                .logout()
//                    .invalidateHttpSession(true)
//                    .logoutSuccessUrl("/login?logout")
//                    /*.and()
//                .exceptionHandling()
//                    .accessDeniedPage("/login?error")*/
//                    .and()
//                .csrf();
        http.authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .successHandler(successHandler())
                .failureUrl("/login?error")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("login?logout")
                .and()
                .csrf();
    }
}
