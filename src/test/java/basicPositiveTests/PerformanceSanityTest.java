package basicPositiveTests;

import base.PerformanceSanityBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.factories.UserBodyFactory;
import org.testng.annotations.Test;
import org.qa.extentreportsmanager.ExtentReportsManager;


@Epic("E2E")
@Feature("Performance sanity tests")
public class PerformanceSanityTest extends PerformanceSanityBaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving single users")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Acquiring a single user");

        singleUser("4");
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("Getting the list of resource");

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving a single resource")
    @Story("Obtaining one resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Obtaining one resource");

        singleResource("2");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Retrieving data via delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        ExtentReportsManager.setTestName("Creating a new person");

        check(getResponse(Method.POST, "/api/users", UserBodyFactory.correct()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when updating a person via PUT")
    @Story("Account update using the PUT method")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Account update using the PUT method");

        check(getResponse(Method.PUT, "/api/users5", UserBodyFactory.toUpdate_PUT()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when updating a person via PATCH")
    @Story("Account update using the PATCH method")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("Account update using the PATCH method");

        check(getResponse(Method.PATCH, "/api/users/4", UserBodyFactory.toUpdate_PATCH()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when registering an account")
    @Story("Account registration")
    public void POST_register() {

        ExtentReportsManager.setTestName("Account registration");

        register(RegisterCredentials.correct());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when logging into an account")
    @Story("Account login")
    public void POST_login() {

        ExtentReportsManager.setTestName("Account login");

        login(LoginCredentials.correct());
    }
}
