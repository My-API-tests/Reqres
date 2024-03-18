import base.BaseTest;
import io.qameta.allure.*;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
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
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Update")
public class UpdateTest extends BaseTest {

    private Response sendRequest(String id, String requestBody) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/api/users/" + id);
    }

    @io.qameta.allure.Step("Verify the <name> data type")
    @io.qase.api.annotation.Step("Verify the <name> data type")
    private void verifyNameDataType(Response response) {

        checkDataType(response, "name", String.class);
    }

    @io.qameta.allure.Step("Verify the <job> data type")
    @io.qase.api.annotation.Step("Verify the <job> data type")
    private void verifyJobDataType(Response response) {

        checkDataType(response, "job", String.class);
    }

    @io.qameta.allure.Step("Verify the <updatedAt> data type")
    @io.qase.api.annotation.Step("Verify the <updatedAt> data type")
    private void verifyUpdatedAtDataType(Response response) {

        checkDataType(response, "updatedAt", String.class);
    }

    @io.qameta.allure.Step("Verify the <name> value")
    @io.qase.api.annotation.Step("Verify the <name> value")
    private void verifyNameValue(Response response, JSONObject requestBody) {

        response.then().assertThat().body("name", equalTo(requestBody.get("name")));
    }

    @io.qameta.allure.Step("Verify the <job> value")
    @io.qase.api.annotation.Step("Verify the <job> value")
    private void verifyJobValue(Response response, JSONObject requestBody) {

        response.then().assertThat().body("job", equalTo(requestBody.get("job")));
    }

    @io.qameta.allure.Step("Verify the <updatedAt> format")
    @io.qase.api.annotation.Step("Verify the <updatedAt> format")
    private void verifyUpdatedAtPropertyFormat(Response response) {

        response.then().assertThat().body("updatedAt", matchesPattern(Patterns.DATE_TIME_FORMAT));
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    @QaseId(15)
    @QaseTitle("Updating a user data using correct credentials")
    @Description("Updating a user data using correct credentials")
    public void correct(JSONObject requestBody) {

        Response response = sendRequest("2", requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.UPDATE_USER);
        verifyNameDataType(response);
        verifyJobDataType(response);
        verifyUpdatedAtDataType(response);
        verifyNameValue(response, requestBody);
        verifyJobValue(response, requestBody);
        verifyUpdatedAtPropertyFormat(response);
    }

    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    @QaseId(16)
    @QaseTitle("Updating a user data missing the <name>")
    @Description("Updating a user data missing the <name>")
    public void missingName(JSONObject requestBody) {

        Response response = sendRequest("2", requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.UPDATE_USER_MISSING_NAME);
        verifyJobDataType(response);
        verifyUpdatedAtDataType(response);
        verifyJobValue(response, requestBody);
        verifyUpdatedAtPropertyFormat(response);
    }

    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    @QaseId(17)
    @QaseTitle("Updating a user data missing the <job>")
    @Description("Updating a user data missing the <job>")
    public void missingJob(JSONObject requestBody) {

        Response response = sendRequest("2", requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.UPDATE_USER_MISSING_JOB);
        verifyNameDataType(response);
        verifyUpdatedAtDataType(response);
        verifyNameValue(response, requestBody);
        verifyUpdatedAtPropertyFormat(response);
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    @QaseId(18)
    @QaseTitle("Updating a user data using an incorrect ID")
    @Description("Updating a user data using an incorrect ID")
    public void incorrectId(JSONObject requestBody) {

        Response response = sendRequest("$$", requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestMessage(response.getBody().asString());
    }

    @Test(dataProvider = DataProviderNames.INCORRECT_KEYS, dataProviderClass = UserDataProviders.class)
    @QaseId(19)
    @QaseTitle("Creating a user with incorrect keys in the request body")
    @Description("Creating a user with incorrect keys in the request body")
    public void incorrectKeys(JSONObject requestBody) {

        Response response = sendRequest("2", requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestMessage(response.getBody().asString());
    }

    @Test
    @QaseId(20)
    @QaseTitle("Updating a user data with malformed request body")
    @Description("Updating a user data with malformed request body")
    public void malformedJSON() {

        String malformedJSON = "{ \"name\": {\"$gt\": \"\"}, \"job\": {\"$ne\": \"\"}";

        Response response = sendRequest("2", malformedJSON);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestMessage(response.getBody().asString());
    }
}
