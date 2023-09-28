package basicPositiveTests;

import base.StatusCodeBaseTest;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;
import static org.qa.constans.SuiteTags.VALIDATE_STATUS_CODE;
import org.qa.bodies.RegisterBody;
import org.qa.bodies.UserBody;

public class StatusCodeTest extends StatusCodeBaseTest {

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void GET_listUsers() {

        check(getResponse(Method.GET, "/api/unknown"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void GET_SingleUser() {

        singleUser("3", HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void GET_ListUsers() {

        check(getResponse(Method.GET, "/api/users?page=2"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void GET_singleResource() {

        singleResource("2", HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 201")
    public void POST_create() {

        check(getResponse(Method.POST, "/api/users/", new UserBody("Pawel", "tester")), HttpStatus.SC_CREATED);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void PUT_update() {

        check(getResponse(Method.PUT, "/api/users/2", new UserBody("Dorotka", "helper")), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void PATCH_update() {

        check(getResponse(Method.PATCH, "/api/users/3", new UserBody("Antosia", "child")), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_register() {

        register(new RegisterBody("eve.holt@reqres.in", "pistol"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_login() {

        register(new RegisterBody("eve.holt@reqres.in", "pistol"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void GET_delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"), HttpStatus.SC_OK);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 204")
    public void DELETE_delete() {

        check(getResponse(Method.DELETE, "/api/users/2"), HttpStatus.SC_NO_CONTENT);
    }
}
