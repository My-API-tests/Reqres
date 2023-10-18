package basicPositiveTests;

import base.HeaderBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.factories.UserBodyFactory;
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

        ExtentReportsManager.setTestName("Getting the user list");

        check(getResponse(Method.GET, "/api/users?page=2"));
    }

    @Test(priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving single users")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Acquiring a single user");

        singleUser("2");
    }

    @Test(priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("Getting the list of resource");

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test(priority = 9)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when receiving a single resource")
    @Story("Obtaining one resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Obtaining one resource");

        singleResource("2");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        ExtentReportsManager.setTestName("Creating a new person");

        check(getResponse(Method.POST, "/api/users", UserBodyFactory.correct()));
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when updating a person via PUT")
    @Story("Account update using the PUT method")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Account update using the PUT method");

        check(getResponse(Method.PUT, "/api/users/1", UserBodyFactory.toUpdate_PUT()));
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when updating a person via PATCH")
    @Story("Account update using the PATCH method")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("Account update using the PATCH method");

        check(getResponse(Method.PATCH,"/api/users/4", UserBodyFactory.toUpdate_PATCH()));
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when registering an account")
    @Story("Account registration")
    public void POST_register() {

        ExtentReportsManager.setTestName("Account registration");

        register(RegisterCredentials.correct());
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when logging into an account")
    @Story("Account login")
    public void POST_login() {

        ExtentReportsManager.setTestName("Account login");

        login(LoginCredentials.correct());
    }

    @Test(priority = 10)
    @Severity(SeverityLevel.MINOR)
    @Description("Checking that HTTPS headers are as expected when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Retrieving data via delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }
}
