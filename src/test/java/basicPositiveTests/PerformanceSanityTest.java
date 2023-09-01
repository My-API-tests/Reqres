package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;
import org.qa.bodies.UserBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.qa.constans.SuiteTags.PERFORMANCE_SANITY;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class PerformanceSanityTest extends BaseTest {

    private void check(Response response) {

        response.then()
                .assertThat()
                .time(lessThanOrEqualTo(500L));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void getSingleUser() {

        check(getResponse(Method.GET,"/api/users/4"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void listUsers() {

        check(getResponse(Method.GET, "/api/users?page=2"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void listResource() {

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void singleResource() {

        check(getResponse(Method.GET, "/api/unknown/2"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void create() {

        check(getResponse(Method.POST, "/api/users", new UserBody("John", "Medic")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void updatePUT() {

        check(getResponse(Method.PUT, "/api/users5", new UserBody("Patrick", "Miner")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void updatePATCH() {

        check(getResponse(Method.PATCH, "/api/users/4", new UserBody("Anna", "Cosmetic")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void register() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void login() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "pistol")));
    }
}
