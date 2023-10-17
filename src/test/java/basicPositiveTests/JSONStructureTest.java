package basicPositiveTests;

import base.JSONStructureBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;
import org.qa.bodies.UserBody;
import reportsManager.ExtentReportsManager;


@Epic("E2E")
@Feature("Testing JSON structure")
public class JSONStructureTest extends JSONStructureBaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving a list of users")
    @Story("Getting the user list")
    public void GET_listUsers() {

        ExtentReportsManager.setTestName("List users");

        check(getResponse(Method.GET, "/api/users?page=2"), "list-users-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving single users")
    @Story("Acquiring a single user")
    public void singleUser() {

        ExtentReportsManager.setTestName("Get single user");

        check(getResponse(Method.GET, "/api/users/2"), "single-user-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("List <Resource>");

        check(getResponse(Method.GET, "/api/unknown"), "list-users-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving a single resource")
    @Story("Obtaining one resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Single <Resource>");

        check(getResponse(Method.GET, "/api/unknown/2"), "single-user-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        ExtentReportsManager.setTestName("Create");

        check(getResponse(Method.POST, "/api/users", new UserBody("Pawel", "Organist")), "create-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when updating a person via PUT")
    @Story("User update using the PUT method")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Updating using PUT");

        check(getResponse(Method.PUT, "/api/users/3", new UserBody("Andy", "Builder")), "create-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when updating a person via PATCH")
    @Story("User update using the PATCH method")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("Updating using PATCH");

        check(getResponse(Method.PATCH, "/api/users/4", new UserBody("Maria", "Geologist")), "create-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when registering an account")
    @Story("Account registration")
    public void POST_register() {

        ExtentReportsManager.setTestName("Register");

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")), "register-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when logging into an account")
    @Story("Account login")
    public void POST_login() {

        ExtentReportsManager.setTestName("Login");

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "cityslicka")), "login-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"), "delayed-response-schema.json");
    }
}
