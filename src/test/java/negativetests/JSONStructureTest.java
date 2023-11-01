package negativetests;

import base.JSONStructureBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.utils.TestType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.qa.extentreportsmanager.ExtentReportsManager;

@Epic("E2E")
@Feature("Testing JSON structure")
public class JSONStructureTest extends JSONStructureBaseTest {

    @BeforeSuite
    public void setTestType() {

        TestType.setType("negative");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to receive a single non-existent user")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_SingleUser() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single user");

        check(getResponse(Method.GET, "/api/users/250"), "negative-valid-input-empty-body.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single resource")
    public void GET_SingleResource() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single resource");

        check(getResponse(Method.GET, "/api/unknown/2000"),"negative-valid-input-empty-body.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        ExtentReportsManager.setTestName("Attempting to register with undefined user");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.notDefinedUser()),"negative-valid-input-register-login.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Account registration without email or username");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.withoutEmailOrUsername()), "negative-valid-input-register-login.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        ExtentReportsManager.setTestName("Account registration without password");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.withoutPassword()), "negative-valid-input-register-login.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        ExtentReportsManager.setTestName("Login with an incorrect username");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.incorrectUsername()), "negative-valid-input-register-login.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to login without email or username")
    @Story("Login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Login without email or username");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.withoutEmailOrUsername()), "negative-valid-input-register-login.json");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure is according to data model when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        ExtentReportsManager.setTestName("Login without password");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.withoutPassword()), "negative-valid-input-register-login.json");
    }
}
