import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SingleResourceTest extends BaseTest {

    private Response check(String id, int statusCode, String jsonSchema) {

        return given()
                .get("/api/unknown/" + id)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(ModelsBuilder.getJsonSchema(jsonSchema)))
                .extract().response();
    }

    @Test
    public void correctId() {

        Response response = check("2", HttpStatus.SC_OK, JSONSchemas.SINGLE_RESOURCE);
    }

    @Test
    public void incorrectId() {

        Response response = check("xyz", HttpStatus.SC_NOT_FOUND, JSONSchemas.EMPTY_BODY);
    }
}
