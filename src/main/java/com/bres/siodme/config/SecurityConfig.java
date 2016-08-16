package com.bres.siodme.config;

/**
 * Created by Adam on 2016-08-02.
 */
import com.bres.siodme.web.security.MySimpleUrlAuthenticationSuccessHandler;
import com.bres.siodme.web.security.UserDetailsServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import java.util.Properties;

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

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(SpringWebConfig.dataSource());
        builder.scanPackages("com.bres.siodme.model")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }



    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    // HTTP Session
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                    .antMatchers("/login").hasRole("ANONYMOUS")
                    .antMatchers("/registration").permitAll()
                    //.anyRequest().permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/welcome").hasRole("USER")
                .and().formLogin()
                    .loginPage("/login")
                    .usernameParameter("username").passwordParameter("password")
                    .successHandler(successHandler())
                    .failureUrl("/login?error")
                .and().logout()
                    .logoutSuccessUrl("/login?logout")
                .and().csrf()
                //.and().sessionManagement()
                //    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                //    .enableSessionUrlRewriting(true)
                ;
    }

}
