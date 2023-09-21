package basicPositiveTests;

import base.PerformanceSanityBaseTest;
import io.restassured.http.Method;
import org.qa.bodies.RegisterBody;
import org.qa.bodies.UserBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;
import static org.qa.constans.SuiteTags.PERFORMANCE_SANITY;


public class PerformanceSanityTest extends PerformanceSanityBaseTest {


    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void GET_listUsers() {

        check(getResponse(Method.GET, "/api/users?page=2"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void GET_singleUser() {

        singleUser("4");
    }


    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void GET_listResource() {

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void GET_singleResource() {

        singleResource("2");
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void GET_delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void POST_create() {

        check(getResponse(Method.POST, "/api/users", new UserBody("John", "Medic")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void PUT_update() {

        check(getResponse(Method.PUT, "/api/users5", new UserBody("Patrick", "Miner")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void PATCH_update() {

        check(getResponse(Method.PATCH, "/api/users/4", new UserBody("Anna", "Cosmetic")));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void POST_register() {

        register(new RegisterBody("eve.holt@reqres.in", "pistol"));
    }

    @Test
    @Tag(PERFORMANCE_SANITY)
    @DisplayName("Response time is less than or equal to 5000 ms")
    public void POST_login() {

        login(new RegisterBody("eve.holt@reqres.in", "pistol"));
    }
}
