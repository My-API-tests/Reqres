package negativeTests.validInput;

import base.HeaderBaseTest;
import io.qameta.allure.*;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

@Epic("E2E")
@Feature("Testing headers")
public class HeadersTest extends HeaderBaseTest {

    @Test(priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when trying to receive a single non-existent user")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_singleUser() {

        singleUser("450");
    }

    @Test(priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single resource")
    public void GET_singleResource() {

        singleResource("3000");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        register(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to register without email or username")
    @Story("Attempting to register without email or username")
    public void POST_registerMissingEmailOrUsername() {

        register(new RegisterBody("", "pistol"));
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to register without password")
    @Story("Attempting to register without password")
    public void POST_registerMissingPassword() {

        register(new RegisterBody("eve.holt@reqres.in", ""));
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to login with an incorrect username")
    @Story("Attempting to login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        login(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to login without email or username")
    @Story("Attempting to login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        login(new RegisterBody("", "pistol"));
    }

    @Test(priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to login without password")
    @Story("Attempting to login without email or username")
    public void POST_loginMissingPassword() {

        login(new RegisterBody("eve.holt@reqres.in", ""));
    }
}
