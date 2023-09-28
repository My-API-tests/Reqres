package basicPositiveTests;

import base.HeaderBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.qa.bodies.UserBody;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;
import reportsManager.ExtentReportsManager;


@Epic("E2E")
@Feature("Testing headers")
public class HeadersTest extends HeaderBaseTest {

    @Test(priority = 6)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving a list of users")
    @Story("Getting the user list")
    public void GET_listUsers() {

        ExtentReportsManager.setTestName("Get list users");

        check(getResponse(Method.GET, "/api/users?page=2"));
    }

    @Test(priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving single users")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Get single user");

        singleUser("2");
    }

    @Test(priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("List <Resource>");

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test(priority = 9)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving a single resource")
    @Story("Obtaining one resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Single <Resource>");

        singleResource("2");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        ExtentReportsManager.setTestName("Create");

        check(getResponse(Method.POST, "/api/users", new UserBody("Pawel", "organist")));
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when updating a person via PUT")
    @Story("Account update using the PUT method")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Updating using PUT");

        check(getResponse(Method.PUT, "/api/users/1", new UserBody("Kate", "Pianist")));
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when updating a person via POST")
    @Story("Account update using the POST method")
    public void POST_update() {

        ExtentReportsManager.setTestName("Updating using POST");

        check(getResponse(Method.PATCH,"/api/users/4", new UserBody("Carlos", "Worker")));
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when registering an account")
    @Story("Account registration")
    public void POST_register() {

        ExtentReportsManager.setTestName("Register");

        register(new RegisterBody("eve.holt@reqres.in", "pistol"));
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when logging into an account")
    @Story("Account login")
    public void POST_login() {

        ExtentReportsManager.setTestName("Login");

        login(new RegisterBody("eve.holt@reqres.in", "cityslicka"));
    }

    @Test(priority = 10)
    @Severity(SeverityLevel.MINOR)
    @Description("Checking that HTTPS headers are as expected when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }
}
