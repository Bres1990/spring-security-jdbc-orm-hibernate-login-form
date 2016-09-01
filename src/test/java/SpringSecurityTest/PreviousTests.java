package SpringSecurityTest;

import com.bres.siodme.config.SecurityConfig;
import com.bres.siodme.config.SpringWebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Adam on 2016-08-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityConfig.class, SpringWebConfig.class})
@WebAppConfiguration
public class PreviousTests {

    private MockMvc mvc;
    @Autowired private WebApplicationContext wac;
    @Autowired private Filter springSecurityFilterChain;
    @Autowired private UserDetailsService userDetailsService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @WithMockUser
    @Test
    public void shouldGrantFreeAccessToLoginPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"))
        ;
    }

    @Test
    public void shouldRejectUnprivilegedUser() throws Exception {
        mvc.perform(get("/welcome").with(user("ukradlemkonto").password("adminowi").roles("ADMIN")))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    public void shouldLetPrivilegedUserPass() throws Exception {
        mvc.perform(get("/welcome").with(user("podajehaslo").password("okon").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("podajehaslo"))
        ;
    }

    @WithMockUser
    @Test
    public void shouldEnsureNoErrorOrMessageAtLogin() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(model().attributeDoesNotExist("message"))
        ;
    }

    @Test
    public void shouldAccessProtectedUrlUponLoginWithCorrectUserDetails() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("username");

        mvc.perform(get("/welcome").with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));
    }

    @Test
    public void shouldRejectLoginAttemptWithIncorrectUserDetails() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("ADMINISTRATOR");

        mvc.perform(get("/welcome").with(user(userDetails)))
                .andExpect(status().isForbidden())
        ;
    }

    @WithMockUser
    @Test
    public void shouldLogoutSuccessfully() throws Exception {
        mvc.perform(SecurityMockMvcRequestBuilders.logout("/login?logout"))
        ;
    }
}