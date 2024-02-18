import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SingleUserTest extends BaseTest {

    private Response check(String user, int statusCode, String schema) {

        return given()
                .get("/api/users/" + user)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(ModelsBuilder.getJsonSchema(schema)))
                .extract().response();
    }

    @Test
    public void correctUserId() {

        Response response = check("2", HttpStatus.SC_OK, JSONSchemas.SINGLE_USER);
    }

    @Test void incorrectUserId() {

        Response response = check("5000", HttpStatus.SC_NOT_FOUND, JSONSchemas.EMPTY_BODY);
    }
}
