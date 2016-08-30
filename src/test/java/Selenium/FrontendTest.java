package Selenium;

/**
 * Created by Adam on 2016-08-30.
 */

import com.bres.siodme.web.repository.UserRepository;
import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

public class FrontendTest extends SeleneseTestBase {

    @Autowired private UserRepository userRepository;
    @Autowired private UserDetailsService userDetailsService;

    WebDriver driver, driverInstance;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.firefox.bin", "E:\\Windows7\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        String baseUrl = "http://localhost:8017/";
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        driverInstance = ((WebDriverBackedSelenium) selenium).getWrappedDriver();
    }

    @Test
    public void shouldPerformLoginToAdminPageAndLogout() {
        loginAs("ADMINISTRATOR", "administration");
        selenium.open("/admin");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("ADMIN | Logout"));

        logout();
        assertTrue(selenium.isTextPresent("You have been logged out successfully."));
    }

    @Test
    public void shouldPerformLoginToWelcomePageAndLogout() {
        loginAs("username", "password");
        selenium.open("/welcome");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Welcome username | Logout"));
        logout();
        assertTrue(selenium.isTextPresent("You have been logged out successfully."));
    }

    @Test
    public void shouldGrantAccessToRegistrationPageViaHttpAddress() {
        selenium.open("/registration");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Create your account"));
    }

    @Test
    public void shouldGrantAccessToRegistrationPageViaLoginPageLink() {
        selenium.open("/login");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Create an account");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Create your account"));
    }

    @Test
    public void shouldRefuseToRegisterDuplicateUsername() {
        registerAs("Termalica", "Nieciecza");
        assertTrue("http://localhost:8017/registration?usernameOccupied=1".equals(driverInstance.getCurrentUrl()));
        assertTrue(selenium.isTextPresent("The username you have chosen is already taken. Try a different one."));
        assertEquals("Create an account", selenium.getTitle());
    }


    @Test
    public void shouldRegisterNewUserSuccessfully() {
        registerAs("WislaKrakow", "MaciejZurawski");
        assertTrue("http://localhost:8017/login?registered=1".equals(driverInstance.getCurrentUrl()));
        assertTrue(selenium.getTitle().contains("Log in"));
    }

    @Test
    public void shouldReturnErrorWhenLoggingWithEmptyForm() {
        loginAs("", "");
        assertTrue(selenium.isTextPresent("Your username and password is invalid."));
        assertTrue("http://localhost:8017/login?error".equals(driverInstance.getCurrentUrl()));
    }

    @Test
    public void shouldReturnErrorWhenUsernameIsRequired() {
        loginAs("", "password");
        assertTrue(selenium.isTextPresent("Your username and password is invalid."));
        assertTrue("http://localhost:8017/login?error".equals(driverInstance.getCurrentUrl()));
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsRequired() {
        loginAs("username", "");
        assertTrue(selenium.isTextPresent("Your username and password is invalid."));
        assertTrue("http://localhost:8017/login?error".equals(driverInstance.getCurrentUrl()));
    }

    @Test
    public void shouldPreventUnauthenticatedUserFromAccessingWelcomePage() {
        selenium.open("/welcome");
        assertTrue(selenium.isTextPresent("Log in"));
        assertTrue(selenium.getTitle().contains("Log in"));
    }

    @Test
    public void shouldPreventUnauthenticatedUserFromAccessingAdminPage() {
        selenium.open("/admin");
        assertTrue(selenium.isTextPresent("Log in"));
        assertTrue(selenium.getTitle().contains("Log in"));
    }

    @Test
    public void shouldPreventUserFromAccessingAdminPage() {
        loginAs("username", "password");
        selenium.open("/admin");
        assertTrue(selenium.isTextPresent("Access is denied"));
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

    private void registerAs(String username, String password) {
        selenium.open("/registration");
        selenium.waitForPageToLoad("5000");
        selenium.type("username", username);
        selenium.type("password", password);
        selenium.type("passwordConfirm", password);
        selenium.click("name=register");
    }

    private void loginAs(String username, String password) {
        selenium.open("/login");
        selenium.waitForPageToLoad("5000");
        selenium.type("username", username);
        selenium.type("password", password);
        selenium.click("name=login");
    }

    private void logout() {
        selenium.click("link=Logout");
    }

}
