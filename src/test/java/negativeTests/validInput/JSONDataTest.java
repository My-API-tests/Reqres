package negativeTests.validInput;

import base.JSONDataBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.factories.UnsuccessfulResponseBodyFactory;
import org.qa.pojo.RegisterLoginUnsuccessfulResponseBody;
import org.testng.annotations.Test;
import reportsManager.ExtentReportsManager;


@Epic("E2E")
@Feature("Testing JSON data")
public class JSONDataTest extends JSONDataBaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to register with an undefined user")
    @Story("Account registration with an undefined user")
    public void POST_registerNotDefinedUser() {

        ExtentReportsManager.setTestName("Account registration with an undefined user");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.notDefinedUser()),
              (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
               UnsuccessfulResponseBodyFactory.forDefinedUsersOnly(), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Account registration without email or username");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.withoutEmailOrUsername()),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodyFactory.missingEmailOrUsername(),true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        ExtentReportsManager.setTestName("Account registration without password");

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.withoutPassword()),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodyFactory.missingPassword(),true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        ExtentReportsManager.setTestName("Login with an incorrect username");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.incorrectUsername()),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodyFactory.userNotFound(), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to login without email or username")
    @Story("Login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Login without email or username");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.withoutEmailOrUsername()),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodyFactory.missingEmailOrUsername(),true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        ExtentReportsManager.setTestName("Login without password");

        check(getResponse(Method.POST, "/api/login", LoginCredentials.withoutPassword()),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodyFactory.missingPassword(),true);
    }
}
