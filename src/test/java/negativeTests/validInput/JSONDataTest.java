package negativeTests.validInput;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.qa.bodies.RegisterBody;
import org.qa.constans.UnsuccessfulResponseBodies;
import org.qa.pojo.BasePojo;
import org.qa.pojo.RegisterLoginUnsuccessfulResponseBody;
import org.qa.utils.Function;
import org.testng.annotations.Test;

public class JSONDataTest extends BaseTest {

    public <T extends BasePojo, X> void check(Response response, Function<Response, T> function, X expected) {

        T given = function.getObject(response);

        Assertions.assertTrue(given.hasValidData(expected));
    }

    @Test
    public void registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")),
              (Response r)->r.body().as(RegisterLoginUnsuccessfulResponseBody.class),
               UnsuccessfulResponseBodies.bodies.get(0));
    }
}
