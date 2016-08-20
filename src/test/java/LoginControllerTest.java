///**
// * Created by Adam on 2016-08-09.
// */
//
//
//import com.bres.siodme.web.controller.LoginController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.servlet.Filter;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//public class LoginControllerTest {
//
//    @Autowired private WebApplicationContext context;
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired private Filter springSecurityFilterChain;
//    @Autowired private UserDetailsService userDetailsService;
//    private LoginController controller;
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        controller = new LoginController();
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .addFilters(springSecurityFilterChain)
//                .defaultRequest(get("/")
//                        .with(user("ADMINISTRATOR").roles("ADMIN"))).build();
//    }
//
//    @Test
//    public void shouldAccessAdminPageUponLogin() throws Exception {
//        mvc.perform(get("/").with(user("ADMINISTRATOR")))
//                .andExpect()
//    }
//
//    @Configuration
//    @EnableWebMvc
//    static class Config extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin();
//        }
//
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth
//                    .inMemoryAuthentication()
//                    .withUser("ADMINISTRATOR").password("administration").roles("USER");
//        }
//
//        @Override
//        @Bean
//        public UserDetailsService userDetailsServiceBean() throws Exception {
//            return super.userDetailsServiceBean();
//        }
//    }
//}