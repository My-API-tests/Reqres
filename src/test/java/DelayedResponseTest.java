import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("E2E")
@Feature("Delayed response")
public class DelayedResponseTest extends BaseTest {

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the users list can be retrieved with a delayed response")
    @Story("As an user, I want to be able to retrieve the list of users even with a delayed response")
    @Test
    public void check() {

        given()
                .get("/api/users?delay=3")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(JSONSchemas.DELAYED_RESPONSE)));
    }
}
