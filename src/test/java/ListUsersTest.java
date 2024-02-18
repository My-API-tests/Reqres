import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ListUsersTest extends BaseTest {

    private Response check(String id, int statusCode, String jsonSchema) {

        return given()
                .get("/api/users?page=" + id)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(ModelsBuilder.getJsonSchema(jsonSchema)))
                .extract().response();
    }

    @Test
    public void correctId() {

        Response response = check("2", HttpStatus.SC_OK, JSONSchemas.LIST_USERS);
    }

    @Test
    public void incorrectId() {

        Response response = check("^%&*", HttpStatus.SC_NOT_FOUND, JSONSchemas.LIST_USERS);
    }
}
