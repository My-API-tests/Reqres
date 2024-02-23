import base.BaseTest;
import io.qameta.allure.*;
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

    private Response check(String user) {

        return given()
                .get("/api/users/" + user);
    }

    @Step("Verify {id, email, first_name, last_name, avatar} data types in the {data} JSON object")
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

    @Step("Verify {url, text} data types in the {support} JSON object")
    private void verifyDataTypesInSupportJSONObject(Response response) {

        response
                .then()
                .assertThat()
                .body("support.url", isA(String.class))
                .body("support.text", isA(String.class));
    }

    @Step("Verify the {email} format")
    private void verifyEmailPropertyValueInResponseWithRequest(Response response) {

                response
                .then()
                .assertThat()
                .body("data.email", matchesPattern(Patterns.EMAIL_FORMAT));
    }

    @Step("Verify the {avatar} format")
    private void verifyAvatarPropertyValueInResponseWithRequest(Response response) {

        response
                .then()
                .assertThat()
                .body("data.avatar", matchesPattern(Patterns.AVATAR_FORMAT));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user data can be retrieved using the correct user ID")
    @Story("As an user, I want to be able to retrieve the user data using the correct user ID")
    @Test
    public void correctUserId() {

        Response response = check("2");
        verifyDataTypesInDataJSONObject(response);
        verifyDataTypesInSupportJSONObject(response);
        verifyEmailPropertyValueInResponseWithRequest(response);
        verifyAvatarPropertyValueInResponseWithRequest(response);
    }

    @Description("Verify that an error message appears when an incorrect user ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect user ID")
    @Test void incorrectUserId() {

        Response response = check("50@@#");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.EMPTY_BODY);
    }
}
