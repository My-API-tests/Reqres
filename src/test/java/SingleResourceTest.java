import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Singe <Resource>")
public class SingleResourceTest extends BaseTest {

    private Response check(String id) {

        return given()
                .get("/api/unknown/" + id);
    }

    @Step("Verify {id, name, year, color, pantone_value} data types in the {data} JSON object")
    private void verifyDataTypesInDataJSONObject(Response response) {

        response
                .then()
                .assertThat()
                .body("data.id", isA(Integer.class))
                .body("data.name", isA(String.class))
                .body("data.year", isA(Integer.class))
                .body("data.color", isA(String.class))
                .body("data.pantone_value", isA(String.class));
    }

    @Step("Verify the {color} format")
    private void verifyColorPropertyValueInResponseWithRequest(Response response) {

        String regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

        response
                .then()
                .assertThat()
                .body("data.color", matchesPattern(regex));
    }

    @Step("Verify that the Pantone value {pantoneValue} format is correct")
    private void verifyPantoneValueFormatInResponse(Response response) {

        String regex = "^([1-9]{2}-2[0-9]{3})$";

        response
                .then()
                .assertThat()
                .body("data.pantone_value", matchesPattern(regex));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the single resource can be retrieved using the correct ID")
    @Story("As an user, I want to be able to retrieve the single resource using the correct ID")
    @Test
    public void correctId() {

        Response response = check("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.SINGLE_RESOURCE);
        verifyDataTypesInDataJSONObject(response);
        verifyColorPropertyValueInResponseWithRequest(response);
        verifyPantoneValueFormatInResponse(response);
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
