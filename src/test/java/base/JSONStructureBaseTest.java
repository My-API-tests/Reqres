package base;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.InputStream;

public class JSONStructureBaseTest extends BaseTest {

    protected void check(Response response, String JsonSchema) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JsonSchema);

        assert inputStream != null;

        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(inputStream));
    }
}
