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
import org.testng.Assert;
import org.testng.annotations.Test;
import reportsManager.ExtentReportsManager;
import java.util.List;


public class HeadersTest extends BaseTest {

    private void check(Response response) {

        List<Header> responseHeaders = response.getHeaders().asList();
        List<String> names = responseHeaders.stream().map(Header::getName).toList();

        Assert.assertTrue(names.contains(ExpectedHeaders.headers.get(0)));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void GET_listUsers() {

        ExtentReportsManager.setTestName("Get list users");

        check(getResponse(Method.GET, "/api/users?page=2"));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Get single user");

        check(getResponse(Method.GET, "/api/users/3"));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void GET_listResource() {

        ExtentReportsManager.setTestName("List <Resource>");

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Single <Resource>");

        check(getResponse(Method.GET, "/api/unknown/2"));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void POST_create() {

        ExtentReportsManager.setTestName("Create");

        check(getResponse(Method.POST, "/api/users", new UserBody("Pawel", "organist")));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Updating using PUT");

        check(getResponse(Method.PUT, "/api/users/1", new UserBody("Kate", "Pianist")));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void POST_update() {

        ExtentReportsManager.setTestName("Updating using POST");

        check(getResponse(Method.PATCH,"/api/users/4", new UserBody("Carlos", "Worker")));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void POST_register() {

        ExtentReportsManager.setTestName("Register");

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void POST_login() {

        ExtentReportsManager.setTestName("Login");

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "cityslicka")));
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void GET_delayedResponse() {

        ExtentReportsManager.setTestName("Delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }
}
