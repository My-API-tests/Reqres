package negativeTests.validInput;

import base.JSONDataBaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;
import org.qa.constans.UnsuccessfulResponseBodies;
import org.qa.pojo.RegisterLoginUnsuccessfulResponseBody;
import org.testng.annotations.Test;


public class JSONDataTest extends JSONDataBaseTest {

    @Test
    public void registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")),
              (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
               UnsuccessfulResponseBodies.bodies.get(0), true);
    }

    @Test
    public void registerMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("", "pistol")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(1),true);
    }

    @Test
    public void registerMissingPassword() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(2),true);
    }

    @Test
    public void loginIncorrectUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@req.in", "pistol")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(3), true);
    }

    @Test
    public void loginMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("", "pistol")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(1),true);
    }

    @Test
    public void loginMissingPassword() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "")),
                (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
                UnsuccessfulResponseBodies.bodies.get(2),true);
    }
}
