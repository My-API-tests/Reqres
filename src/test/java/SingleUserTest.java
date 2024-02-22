import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Single user")
public class SingleUserTest extends BaseTest {

    private Response check(String user, int statusCode, String jsonSchemaKey) {

        return given()
                .get("/api/users/" + user)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(jsonSchemaKey)))
                .extract().response();
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

        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

                response
                .then()
                .assertThat()
                .body("data.color", matchesPattern(regex));
    }

    @Step("Verify the {avatar} format")
    private void verifyAvatarPropertyValueInResponseWithRequest(Response response) {

        String regex = "^(https:\\/\\/reqres\\.in\\/img\\/faces\\/.+\\.jpg)$";

        response
                .then()
                .assertThat()
                .body("data.color", matchesPattern(regex));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user data can be retrieved using the correct user ID")
    @Story("As an user, I want to be able to retrieve the user data using the correct user ID")
    @Test
    public void correctUserId() {

        Response response = check("2", HttpStatus.SC_OK, JSONSchemas.SINGLE_USER);
        verifyDataTypesInDataJSONObject(response);
        verifyDataTypesInSupportJSONObject(response);
        verifyEmailPropertyValueInResponseWithRequest(response);
        verifyAvatarPropertyValueInResponseWithRequest(response);
    }

    @Description("Verify that an error message appears when an incorrect user ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect user ID")
    @Test void incorrectUserId() {

        Response response = check("5000", HttpStatus.SC_NOT_FOUND, JSONSchemas.EMPTY_BODY);
    }
}
