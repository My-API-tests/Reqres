import base.BaseTest;
import io.qameta.allure.*;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.support.JSONSchemas;
import org.qa.support.Patterns;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Single <Resource>")
public class SingleResourceTest extends BaseTest {

    @io.qameta.allure.Step("Perform a GET request to https://reqres.in/api/unknown/<ID>, where <ID> represents a single <Resource> ID number")
    @io.qase.api.annotation.Step("Perform a GET request to https://reqres.in/api/unknown/<ID>, where <ID> represents a single <Resource> ID number")
    private Response sendRequest(String id) {

        return given()
                .get("/api/unknown/" + id);
    }

    @io.qameta.allure.Step("Verify {id, name, year, color, pantone_value} data types in the {data} JSON object")
    @io.qase.api.annotation.Step("Verify {id, name, year, color, pantone_value} data types in the {data} JSON object")
    private void verifyDataTypesInDataJSONObject(Response response) {

        checkDataType(response, "data.id", Integer.class);
        checkDataType(response, "data.name", String.class);
        checkDataType(response, "data.year", Integer.class);
        checkDataType(response, "data.color", String.class);
        checkDataType(response, "data.pantone_value", Integer.class);
    }

    @io.qameta.allure.Step("Verify the {color} format")
    @io.qase.api.annotation.Step("Verify the {color} format")
    private void verifyColorPropertyValueInResponseWithRequest(Response response) {

        response
                .then()
                .assertThat()
                .body("data.color", matchesPattern(Patterns.COLOR_FORMAT));
    }

    @io.qameta.allure.Step("Verify the Pantone {pantone_value} format")
    @io.qase.api.annotation.Step("Verify the Pantone {pantone_value} format")
    private void verifyPantoneValueFormatInResponse(Response response) {

        response
                .then()
                .assertThat()
                .body("data.pantone_value", matchesPattern(Patterns.PANTONE_FORMAT));
    }

    @Test
    @QaseId(5)
    @QaseTitle("Getting a single <Resource> using a correct resource ID")
    @Description("Getting a single <Resource> using a correct resource ID")
    public void correctId() {

        Response response = sendRequest("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.SINGLE_RESOURCE);
        verifyDataTypesInDataJSONObject(response);
        verifyColorPropertyValueInResponseWithRequest(response);
        verifyPantoneValueFormatInResponse(response);
        verifyHeaders(response);
    }

    @Test
    @QaseId(6)
    @QaseTitle("Getting a single <Resource> using an incorrect resource ID")
    @Description("Getting a single <Resource> using an incorrect resource ID")
    public void incorrectId() {

        Response response = sendRequest("xyz");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.EMPTY_BODY);
        verifyHeaders(response);
    }
}
