import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

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

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user data can be retrieved using the correct user ID")
    @Story("As an user, I want to be able to retrieve the user data using the correct user ID")
    @Test
    public void correctUserId() {

        Response response = check("2", HttpStatus.SC_OK, JSONSchemas.SINGLE_USER);
    }

    @Description("Verify that an error message appears when an incorrect user ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect user ID")
    @Test void incorrectUserId() {

        Response response = check("5000", HttpStatus.SC_NOT_FOUND, JSONSchemas.EMPTY_BODY);
    }
}
