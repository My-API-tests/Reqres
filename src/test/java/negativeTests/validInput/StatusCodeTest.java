package negativeTests.validInput;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

import static org.qa.constans.SuiteTags.VALIDATE_STATUS_CODE;

public class StatusCodeTest extends BaseTest {

    private void check(Response response, int expectedStatusCode) {

        response.then()
                .assertThat()
                .statusCode(expectedStatusCode);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getSingleUser() {

        check(getResponse(Method.GET, "/api/users/150"), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getSingleResource() {

        check(getResponse(Method.GET, "/api/unknown/2000"), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void registerMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("", "pistol")), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void registerMissingPassword() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "")), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void loginIncorrectUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@req.in", "pistol")), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void loginMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("", "pistol")), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void loginMissingPassword() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "")), HttpStatus.SC_BAD_REQUEST);
    }
}
