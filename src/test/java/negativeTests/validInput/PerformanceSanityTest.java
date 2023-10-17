package negativeTests.validInput;

import base.PerformanceSanityBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;


public class PerformanceSanityTest extends PerformanceSanityBaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to receive a single non-existent user")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_singleUser() {

        singleUser("500");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single resource")
    public void GET_singleResource() {

        singleResource("2000");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        register(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        register(new RegisterBody("", "pistol"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        register(new RegisterBody("eve.holt@reqres.in", ""));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        login(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to login without email or username")
    @Story("Login without username")
    public void POST_loginMissingEmailOrUsername() {

        login(new RegisterBody("", "pistol"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        login(new RegisterBody("eve.holt@reqres.in", ""));
    }
}
