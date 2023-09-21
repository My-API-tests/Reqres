package negativeTests.validInput;

import base.JSONStructureBaseTest;
import io.restassured.http.Method;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

public class JSONStructureTest extends JSONStructureBaseTest {

    @Test
    public void getSingleUser() {

        check(getResponse(Method.GET, "/api/users/250"), "negative-valid-input-empty-body.json");
    }

    @Test
    public void getSingleResource() {

        check(getResponse(Method.GET, "/api/unknown/2000"),"negative-valid-input-empty-body.json");
    }

    @Test
    public void registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")),"negative-valid-input-register-login.json");
    }

    @Test
    public void registerMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("", "pistol")), "negative-valid-input-register-login.json");
    }

    @Test
    public void registerMissingPassword() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "")), "negative-valid-input-register-login.json");
    }

    @Test
    public void loginIncorrectUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@req.in", "pistol")), "negative-valid-input-register-login.json");
    }

    @Test
    public void loginMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("", "pistol")), "negative-valid-input-register-login.json");
    }

    @Test
    public void loginMissingPassword() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "")), "negative-valid-input-register-login.json");
    }
}
