package negativeTests.validInput;

import base.JSONStructureBaseTest;
import io.restassured.http.Method;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

public class JSONStructureTest extends JSONStructureBaseTest {

    @Test
    public void GET_SingleUser() {

        check(getResponse(Method.GET, "/api/users/250"), "negative-valid-input-empty-body.json");
    }

    @Test
    public void GET_SingleResource() {

        check(getResponse(Method.GET, "/api/unknown/2000"),"negative-valid-input-empty-body.json");
    }

    @Test
    public void POST_registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")),"negative-valid-input-register-login.json");
    }

    @Test
    public void POST_registerMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("", "pistol")), "negative-valid-input-register-login.json");
    }

    @Test
    public void POST_registerMissingPassword() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "")), "negative-valid-input-register-login.json");
    }

    @Test
    public void POST_loginIncorrectUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@req.in", "pistol")), "negative-valid-input-register-login.json");
    }

    @Test
    public void POST_loginMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("", "pistol")), "negative-valid-input-register-login.json");
    }

    @Test
    public void POST_loginMissingPassword() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "")), "negative-valid-input-register-login.json");
    }
}
