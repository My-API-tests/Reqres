package negativeTests.validInput;

import base.JSONDataBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;
import org.qa.constans.UnsuccessfulResponseBodies;
import org.qa.pojo.RegisterLoginUnsuccessfulResponseBody;
import org.testng.annotations.Test;


@Epic("E2E")
@Feature("Testing JSON data")
public class JSONDataTest extends JSONDataBaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to register with an undefined user")
    @Story("Account registration with an undefined user")
    public void POST_registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")),
              (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
               UnsuccessfulResponseBodies.bodies.get(0), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("", "pistol")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(1),true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(2),true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@req.in", "pistol")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(3), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to login without email or username")
    @Story("Login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("", "pistol")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(1),true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(2),true);
    }
}
