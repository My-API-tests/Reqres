package basicPositiveTests;

import base.BaseTest;
import static org.qa.constans.SuiteTags.VALIDATE_HEADERS;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.UserBody;
import org.qa.constans.ExpectedHeaders;
import org.qa.bodies.RegisterBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import java.util.List;
import java.util.Map;

public class HeadersTest extends BaseTest {

    private void check(Response response, Map<String, String> headers) {

        List<Header> expectedHeaders = headers.entrySet().stream()
                .map(x->new Header(x.getKey(), x.getValue())).toList();

        List<Header> givenHeaders = response.getHeaders().asList();

        assertThat(givenHeaders, containsInAnyOrder(expectedHeaders));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void getSingleUser() {

        check(getResponse(Method.GET, "/api/users/3"), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void listUsers() {

        check(getResponse(Method.GET, "/api/users?page=2"), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void listResource() {

        check(getResponse(Method.GET, "/api/unknown"), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void singleResource() {

        check(getResponse(Method.GET, "/api/unknown/2"), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void create() {

        //check(getResponse(Method.POST, "/api/users", new UserBody("Pawel", "organist")), ExpectedHeaders.myHeaders);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void updatePUT() {

        check(getResponse(Method.PUT, "/api/users/1", new UserBody("Kate", "Pianist")), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void updatePOST() {

        check(getResponse(Method.PATCH,"/api/users/4", new UserBody("Carlos", "Worker")), ExpectedHeaders.myHeaders);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void register() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void login() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "cityslicka")), ExpectedHeaders.headers);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"), ExpectedHeaders.headers);
    }
}
