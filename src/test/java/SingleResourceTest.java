import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("E2E")
@Feature("Singe <Resource>")
public class SingleResourceTest extends BaseTest {

    private Response check(String id) {

        return given()
                .get("/api/unknown/" + id);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the single resource can be retrieved using the correct ID")
    @Story("As an user, I want to be able to retrieve the single resource using the correct ID")
    @Test
    public void correctId() {

        Response response = check("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.SINGLE_RESOURCE);
    }

    @Description("Verify that an error message appears when an incorrect ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect ID")
    @Test
    public void incorrectId() {

        Response response = check("xyz");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.EMPTY_BODY);
    }
}
