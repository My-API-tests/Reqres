package base;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;

import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class PerformanceSanityBaseTest extends BaseTest {

    protected void check(Response response) {

        response.then()
                .assertThat()
                .time(lessThanOrEqualTo(500L));
    }

    protected void singleUser(String userNumber) {

        check(getResponse(Method.GET,"/api/users/" + userNumber));
    }

    protected void singleResource(String userNumber) {

        check(getResponse(Method.GET, "/api/unknown/" + userNumber));
    }

    protected void register(RegisterBody registerBody) {

        check(getResponse(Method.POST, "/api/register", registerBody));
    }

    protected void login(RegisterBody registerBody) {

        check(getResponse(Method.POST, "/api/login", registerBody));
    }
}
