import base.BaseTest;
import io.qameta.allure.*;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.support.JSONSchemas;
import org.qa.support.Patterns;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Single user")
public class SingleUserTest extends BaseTest {

    @io.qameta.allure.Step("Perform a GET request to https://reqres.in/api/user/<ID>, where <ID> represents a user ID number")
    @io.qase.api.annotation.Step("Perform a GET request to https://reqres.in/api/user/<ID>, where <ID> represents a user ID number")
    private Response check(String user) {

        return given()
                .get("/api/users/" + user);
    }

    @io.qameta.allure.Step("Verify {id, email, first_name, last_name, avatar} data types in the {data} JSON object")
    @io.qase.api.annotation.Step("Verify {id, email, first_name, last_name, avatar} data types in the {data} JSON object")
    private void verifyDataTypesInDataJSONObject(Response response) {

        response
                .then()
                .assertThat()
                .body("data.id", isA(Integer.class))
                .body("data.email", isA(String.class))
                .body("data.first_name", isA(String.class))
                .body("data.last_name", isA(String.class))
                .body("data.avatar", isA(String.class));
    }

    @io.qameta.allure.Step("Verify {url, text} data types in the {support} JSON object")
    @io.qase.api.annotation.Step("Verify {url, text} data types in the {support} JSON object")
    private void verifyDataTypesInSupportJSONObject(Response response) {

        response
                .then()
                .assertThat()
                .body("support.url", isA(String.class))
                .body("support.text", isA(String.class));
    }

    @io.qameta.allure.Step("Verify the {email} format")
    @io.qase.api.annotation.Step("Verify the {email} format")
    private void verifyEmailPropertyValueInResponseWithRequest(Response response) {

                response
                .then()
                .assertThat()
                .body("data.email", matchesPattern(Patterns.EMAIL_FORMAT));
    }

    @io.qameta.allure.Step("Verify the {avatar} format")
    @io.qase.api.annotation.Step("Verify the {avatar} format")
    private void verifyAvatarPropertyValueInResponseWithRequest(Response response) {

        response
                .then()
                .assertThat()
                .body("data.avatar", matchesPattern(Patterns.AVATAR_FORMAT));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @QaseId(1)
    @QaseTitle("Getting a user data using an existing user ID")
    @Description("Getting a user data using an existing user ID")
    public void existingUserId() {

        Response response = check("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyDataTypesInDataJSONObject(response);
        verifyDataTypesInSupportJSONObject(response);
        verifyEmailPropertyValueInResponseWithRequest(response);
        verifyAvatarPropertyValueInResponseWithRequest(response);
        verifyHeaders(response);
    }

    @Test
    @QaseId(2)
    @QaseTitle("Getting a user data using a non existing user ID")
    @Description("Getting a user data using a non existing user ID")
    void nonExistingUserId() {

        Response response = check("13");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.EMPTY_BODY);
        verifyHeaders(response);
    }
}
