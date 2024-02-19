import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.qa.dataproviders.UserDataProviders;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.DataProviderNames;
import org.qa.utils.JSONSchemas;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("E2E")
@Feature("Update")
public class UpdateTest extends BaseTest {

    private Response check(String id, int statusCode, JSONObject body, String jsonSchemaKey) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .put("/api/users/" + id)
                .then()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(jsonSchemaKey)))
                .extract().response();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user's data is updated successfully using correct data")
    @Story("As an user, I want to be able to update a user data using correct data")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void correct(JSONObject body) {

        Response response = check("2", HttpStatus.SC_OK, body, JSONSchemas.UPDATE_USER);

        response.then()
                .assertThat()
                .body("name", equalTo(body.getString("name")))
                .body("job", equalTo(body.getString("job")));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user's data is updated successfully when the \"name\" parameter is missing")
    @Story("As a user, I want to be able to update my data even if I do not provide my name")
    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    public void missingName(JSONObject body) {

        Response response = check("2", HttpStatus.SC_OK, body, JSONSchemas.UPDATE_USER);

        response.then()
                .assertThat()
                .body("job", equalTo(body.getString("job")));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user's data is updated successfully when the \"job\" parameter is missing")
    @Story("As a user, I want to be able to update my data even if I do not provide my job")
    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    public void withoutJob(JSONObject body) {

        Response response = check("2", HttpStatus.SC_OK, body, JSONSchemas.UPDATE_USER);

        response.then()
                .assertThat()
                .body("name", equalTo(body.getString("name")));
    }

    @Description("Verify that an error message appears when an incorrect user ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect user ID")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void incorrectId(JSONObject body) {

        Response response = check("10000", HttpStatus.SC_NOT_FOUND, body, JSONSchemas.EMPTY_BODY);
    }

    @Description("Verify that an error message appears when sending a malformed JSON request body")
    @Story("As a user, I want to see an error message when I provide an incorrect request body format")
    @Test
    public void malformedJSON() {

        String invalid = "{ \"name\": {\"$gt\": \"\"}, \"job\": {\"$ne\": \"\"}";

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

        Assert.assertEquals(preContent, "Bad Request");
    }
}
