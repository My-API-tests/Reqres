package basicPositiveTests;

import base.StatusCodeBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.qa.bodies.RegisterBody;
import org.qa.bodies.UserBody;

public class StatusCodeTest extends StatusCodeBaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        check(getResponse(Method.GET, "/api/unknown"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving single users")
    @Story("Acquiring a single user")
    public void GET_SingleUser() {

        singleUser("3", HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving a list of users")
    @Story("Getting the user list")
    public void GET_ListUsers() {

        check(getResponse(Method.GET, "/api/users?page=2"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving a list of users")
    @Story("Getting the user list")
    public void GET_singleResource() {

        singleResource("2", HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 201 when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        check(getResponse(Method.POST, "/api/users/", new UserBody("Pawel", "tester")), HttpStatus.SC_CREATED);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when updating a person via PUT")
    @Story("Account update using the PUT method")
    public void PUT_update() {

        check(getResponse(Method.PUT, "/api/users/2", new UserBody("Dorotka", "helper")), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when updating a person via PATCH")
    @Story("Account update using the PATCH method")
    public void PATCH_update() {

        check(getResponse(Method.PATCH, "/api/users/3", new UserBody("Antosia", "child")), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when registering an account")
    @Story("Account registration")
    public void POST_register() {

        register(new RegisterBody("eve.holt@reqres.in", "pistol"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when logging into an account")
    @Story("Account login")
    public void POST_login() {

        register(new RegisterBody("eve.holt@reqres.in", "pistol"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 200 when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"), HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 204 when receiving delete an user")
    @Story("Deleting an user")
    public void DELETE_delete() {

        check(getResponse(Method.DELETE, "/api/users/2"), HttpStatus.SC_NO_CONTENT);
    }
}
