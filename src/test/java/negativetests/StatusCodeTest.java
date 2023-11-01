package negativetests;

import base.StatusCodeBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.qa.extentreportsmanager.ExtentReportsManager;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.utils.TestType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


@Epic("E2E")
@Feature("Status code tests")
public class StatusCodeTest extends StatusCodeBaseTest {

    @BeforeSuite
    public void setTestType() {

        TestType.setType("negative");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 404 when trying to receive a single non-existent user")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single user");

        singleUser("150", HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 404 when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single resource");

        singleResource("2000", HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        ExtentReportsManager.setTestName("Attempting to register with undefined user");

        register(RegisterCredentials.notDefinedUser(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Account registration without email or username");

        register(RegisterCredentials.withoutEmailOrUsername(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        ExtentReportsManager.setTestName("Account registration without password");

        register(RegisterCredentials.withoutPassword(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        ExtentReportsManager.setTestName("Login with an incorrect username");

        login(LoginCredentials.incorrectUsername(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login without email or username")
    @Story("Login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Login without username");

        login(LoginCredentials.withoutEmailOrUsername(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        ExtentReportsManager.setTestName("Login without password");

        login(LoginCredentials.withoutPassword(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 404 when trying to delete an user that does not exist")
    @Story("Deleting an user that does not exist")
    public void DELETE_delete() {

        ExtentReportsManager.setTestName("Delete an user that does not exists");

        check(getResponse(Method.DELETE, "/api/users/23332"), HttpStatus.SC_NOT_FOUND);
    }
}
