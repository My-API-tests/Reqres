package negativeTests.validInput;

import base.StatusCodeBaseTest;
import io.qameta.allure.*;
import org.apache.http.HttpStatus;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.testng.annotations.Test;


@Epic("E2E")
@Feature("Status code tests")
public class StatusCodeTest extends StatusCodeBaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 404 when trying to receive a single non-existent user")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        singleUser("150", HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 404 when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_singleResource() {

        singleResource("2000", HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        register(RegisterCredentials.notDefinedUser(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        register(RegisterCredentials.withoutEmailOrUsername(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        register(RegisterCredentials.withoutPassword(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        login(LoginCredentials.incorrectUsername(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login without email or username")
    @Story("Login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        login(LoginCredentials.withoutEmailOrUsername(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        login(LoginCredentials.withoutPassword(), HttpStatus.SC_BAD_REQUEST);
    }
}
