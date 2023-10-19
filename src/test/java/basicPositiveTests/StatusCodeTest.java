package basicPositiveTests;

import base.StatusCodeBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.factories.UserBodyFactory;
import org.qa.utils.TestType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.qa.extentreportsmanager.ExtentReportsManager;


@Epic("E2E")
@Feature("Status code tests")
public class StatusCodeTest extends StatusCodeBaseTest {

    @BeforeSuite
    public void setTestType() {

        TestType.setType("positive");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("Getting the list of resource");

        check(getResponse(Method.GET, "/api/unknown"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving single users")
    @Story("Acquiring a single user")
    public void GET_SingleUser() {

        ExtentReportsManager.setTestName("Acquiring a single user");

        singleUser("3", HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving a list of users")
    @Story("Getting the user list")
    public void GET_ListUsers() {

        ExtentReportsManager.setTestName("Getting the user list");

        check(getResponse(Method.GET, "/api/users?page=2"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving a list of users")
    @Story("Getting the user list")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Getting the user list");

        singleResource("2", HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 201 when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        ExtentReportsManager.setTestName("Creating a new person");

        check(getResponse(Method.POST, "/api/users/", UserBodyFactory.correct()), HttpStatus.SC_CREATED);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when updating a person via PUT")
    @Story("Account update using the PUT method")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Account update using the PUT method");

        check(getResponse(Method.PUT, "/api/users/2", UserBodyFactory.toUpdate_PUT()), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when updating a person via PATCH")
    @Story("Account update using the PATCH method")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("Account update using the PATCH method");

        check(getResponse(Method.PATCH, "/api/users/3", UserBodyFactory.toUpdate_PATCH()), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when registering an account")
    @Story("Account registration")
    public void POST_register() {

        ExtentReportsManager.setTestName("Account registration");

        register(RegisterCredentials.correct(), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when logging into an account")
    @Story("Account login")
    public void POST_login() {

        ExtentReportsManager.setTestName("Account login");

        register(LoginCredentials.correct(), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Retrieving data via delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 204 when receiving delete an user")
    @Story("Deleting an user")
    public void DELETE_delete() {

        ExtentReportsManager.setTestName("Deleting an user");

        check(getResponse(Method.DELETE, "/api/users/2"), HttpStatus.SC_NO_CONTENT);
    }
}
