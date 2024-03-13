import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.qa.dataproviders.UserDataProviders;
import org.qa.support.DataProviderNames;
import org.qa.support.JSONSchemas;
import org.qa.support.Patterns;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Update")
public class UpdateTest extends BaseTest {

    private Response check(String id, String requestBody) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/api/users/" + id);
    }

    @Step("Verify {name, job, updatedAt} data types")
    private void verifyDataTypesInResponse(Response response) {

        response
                .then()
                .assertThat()
                .body("name", isA(String.class))
                .body("job", isA(String.class))
                .body("updatedAt", isA(String.class));
    }

    @Step("Verify {job, createdAt} data types")
    private void verifyDataTypesWhenMissingName(Response response) {

        checkDataType(response, "job", String.class);
        checkDataType(response, "updatedAt", String.class);
    }

    @Step("Verify {name, job, updatedAt} values")
    private void verifyValuesInResponseWithRequest(Response response, JSONObject requestBody) {

        response
                .then()
                .assertThat()
                .body("name", equalTo(requestBody.getString("name")))
                .body("job", equalTo(requestBody.getString("job")));
    }

    @Step("Verify the {createdAt} format")
    private void verifyCreatedAtPropertyValueInResponseWithRequest(Response response) {

        response
                .then()
                .assertThat()
                .body("createdAt", matchesPattern(Patterns.DATE_TIME_FORMAT));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user's data is updated successfully using correct data")
    @Story("As an user, I want to be able to update a user data using correct data")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void correct(JSONObject body) {

        Response response = check("2", body.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.UPDATE_USER);
        verifyDataTypesInResponse(response);
        verifyValuesInResponseWithRequest(response, body);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user's data is updated successfully when the \"name\" parameter is missing")
    @Story("As a user, I want to be able to update my data even if I do not provide my name")
    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    public void missingName(JSONObject body) {

        Response response = check("2", body.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.UPDATE_USER);
        verifyDataTypesWhenMissingName(response);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user's data is updated successfully when the \"job\" parameter is missing")
    @Story("As a user, I want to be able to update my data even if I do not provide my job")
    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    public void withoutJob(JSONObject body) {

        Response response = check("2", body.toString());
        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
    }

    @Description("Verify that an error message appears when an incorrect user ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect user ID")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void incorrectId(JSONObject body) {

        Response response = check("10000", body);
        System.out.println(response.statusCode());
        System.out.println(response.getBody().prettyPrint());
    }

    @Description("Verify that an error message appears when sending a malformed JSON request body")
    @Story("As a user, I want to see an error message when I provide an incorrect request body format")
    @Test
    public void malformedJSON() {

        /*String invalid = "{ \"name\": {\"$gt\": \"\"}, \"job\": {\"$ne\": \"\"}";

        String responseHTML = given()
                .contentType(ContentType.JSON)
                .body(invalid)
                .put("/api/users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body().asString();

        Document document = Jsoup.parse(responseHTML);
        String preContent = document.select("pre").text();

        Assert.assertEquals(preContent, "Bad Request");*/
    }
}
