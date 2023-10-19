package basicPositiveTests;

import base.JSONStructureBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.factories.UserBodyFactory;
import org.qa.utils.TestType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.qa.extentreportsmanager.ExtentReportsManager;


@Epic("E2E")
@Feature("Testing JSON structure")
public class JSONStructureTest extends JSONStructureBaseTest {

    @BeforeSuite
    public void setTestType() {

        TestType.setType("positive");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving a list of users")
    @Story("Getting the user list")
    public void GET_listUsers() {

        ExtentReportsManager.setTestName("Getting the user list");

        check(getResponse(Method.GET, "/api/users?page=2"), "list-users-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving single users")
    @Story("Acquiring a single user")
    public void singleUser() {

        ExtentReportsManager.setTestName("Acquiring a single user");

        check(getResponse(Method.GET, "/api/users/2"), "single-user-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("Getting the list of resource");

        check(getResponse(Method.GET, "/api/unknown"), "list-users-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving a single resource")
    @Story("Obtaining one resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Obtaining one resource");

        check(getResponse(Method.GET, "/api/unknown/2"), "single-user-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        ExtentReportsManager.setTestName("Creating a new person");

        check(getResponse(Method.POST, "/api/users", UserBodyFactory.correct()), "create-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when updating a person via PUT")
    @Story("User update using the PUT method")
    public void PUT_update() {

        ExtentReportsManager.setTestName("User update using the PUT method");

        check(getResponse(Method.PUT, "/api/users/3", UserBodyFactory.toUpdate_PUT()), "create-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when updating a person via PATCH")
    @Story("User update using the PATCH method")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("User update using the PATCH method");

        check(getResponse(Method.PATCH, "/api/users/4", UserBodyFactory.toUpdate_PATCH()), "create-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when registering an account")
    @Story("Account registration")
    public void POST_register() {

        ExtentReportsManager.setTestName("Account registration");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.correct()), "register-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when logging into an account")
    @Story("Account login")
    public void POST_login() {

        ExtentReportsManager.setTestName("Account login");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.correct()), "login-json-schema.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Retrieving data via delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"), "delayed-response-schema.json");
    }
}
