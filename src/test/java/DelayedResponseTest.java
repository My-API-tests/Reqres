import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DelayedResponseTest extends BaseTest {

    @Test
    public void check() {

        given()
                .get("/api/users?delay=3")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchema(ModelsBuilder.getJsonSchema(JSONSchemas.DELAYED_RESPONSE)));
    }
}
