package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.qa.constans.SuiteTags.STATUS_CODE;
import org.qa.bodies.RegisterBody;
import org.qa.bodies.UserBody;

public class StatusCodeTest extends BaseTest {

    private void check(Response response, int expectedStatusCode) {

        response.then()
                .assertThat()
                .statusCode(expectedStatusCode);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getListUsers() {

        check(getResponse(Method.GET, "/api/users?page=2"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getSingleUser() {

        check(getResponse(Method.GET, "/api/users/3"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getList() {

        check(getResponse(Method.GET, "/api/unknown"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getSingleResource() {

        check(getResponse(Method.GET, "/api/unknown/2"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 201")
    public void create() {

        check(getResponse(Method.POST, "/api/users/", new UserBody("Pawel", "tester")), HttpStatus.SC_CREATED);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void updatePUT() {

        check(getResponse(Method.PUT, "/api/users/2", new UserBody("Dorotka", "helper")), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void updatePATCH() {

        check(getResponse(Method.PATCH, "/api/users/3", new UserBody("Antosia", "child")), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void register() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void login() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "pistol")), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 204")
    public void delete() {

        check(getResponse(Method.DELETE, "/api/users/2"), HttpStatus.SC_NO_CONTENT);
    }
}
