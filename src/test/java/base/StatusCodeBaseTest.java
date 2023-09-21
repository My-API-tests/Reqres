package base;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;

public class StatusCodeBaseTest extends BaseTest {

    protected void check(Response response, int expectedStatusCode) {

        response.then()
                .assertThat()
                .statusCode(expectedStatusCode);
    }

    public void singleUser(String userNumber, int httpStatus) {

        check(getResponse(Method.GET, "/api/users/" + userNumber), httpStatus);
    }

    public void singleResource(String userNumber, int httpStatus) {

        check(getResponse(Method.GET, "/api/unknown/" + userNumber), httpStatus);
    }

    public void register(RegisterBody registerBody, int httpStatus) {

        check(getResponse(Method.POST, "/api/register", registerBody), httpStatus);
    }

    public void login(RegisterBody registerBody, int httpStatus) {

        check(getResponse(Method.POST, "/api/login", registerBody), httpStatus);
    }
}
