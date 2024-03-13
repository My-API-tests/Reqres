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
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@Epic("E2E")
@Feature("Create")
public class CreateUserTest extends BaseTest {

    @io.qameta.allure.Step("Perform a POST request to https://reqres.in/api/users")
    @io.qase.api.annotation.Step("Perform a POST request to https://reqres.in/api/users")
    private Response sendRequest(String requestBody) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/users/");
    }

    @Step("Verify {name, job, id, createdAt} data types")
    private void verifyDataTypesInResponse(Response response) {

        checkDataType(response, "name", String.class);
        checkDataType(response, "job", String.class);
        checkDataType(response, "id", String.class);
        checkDataType(response, "createdAt", String.class);
    }

    @Step("Verify {job, id, createdAt} data type")
    private void verifyDataTypesWhenMissingName(Response response) {

        checkDataType(response, "job", String.class);
        checkDataType(response, "id", String.class);
        checkDataType(response, "createdAt", String.class);
    }

    @Step("Verify {name, id, createdAt} data type")
    private void verifyDataTypesWhenMissingJob(Response response) {

        checkDataType(response, "name", String.class);
        checkDataType(response, "id", String.class);
        checkDataType(response, "createdAt", String.class);
    }

    @Step("Verify {id, createdAt} data type")
    private void verifyDataTypesWhenEmptyRequestBody(Response response) {

        checkDataType(response, "id", String.class);
        checkDataType(response, "createdAt", String.class);
    }

    @Step("Verify {name, job} values")
    private void verifyValuesInResponseWithRequest(Response response, JSONObject requestBody) {

        response.then()
                .assertThat()
                .body("name", equalTo(requestBody.getString("name")))
                .body("job", equalTo(requestBody.getString("job")));
    }

    @Step("Verify the {job} value")
    private void verifyJobValueInResponseWithRequest(Response response, JSONObject requestBody) {

        response
                .then()
                .assertThat()
                .body("job", equalTo(requestBody.getString("job")));
    }

    @Step("Verify the {name} value")
    private void verifyNameValueInResponseWithRequest(Response response, JSONObject requestBody) {

        response
                .then()
                .assertThat()
                .body("name", equalTo(requestBody.get("name")));
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    @QaseId(9)
    @QaseTitle("Creating a user using correct credentials")
    @Description("Creating a user using correct credentials")
    public void correct(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.CREATE_USER);
        verifyDataTypesInResponse(response);
        verifyValuesInResponseWithRequest(response, requestBody);
    }

    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    @QaseId(10)
    @QaseTitle("Creating a user missing the {name}")
    @Description("Creating a user missing the {name}")
    public void missingName(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.MISSING_NAME);
        verifyDataTypesWhenMissingName(response);
        verifyJobValueInResponseWithRequest(response, requestBody);
        verifyHeaders(response);
    }

    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    @QaseId(11)
    @QaseTitle("Creating a user missing the {job}")
    @Description("Creating a user missing the {job}")
    public void missingJob(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.MISSING_JOB);
        verifyDataTypesWhenMissingJob(response);
        verifyNameValueInResponseWithRequest(response, requestBody);
        verifyHeaders(response);
    }

    @Test
    @QaseId(12)
    @QaseTitle("Creating a user with empty request body")
    @Description("Creating a user with empty request body")
    public void emptyRequestBody() {

        Response response = sendRequest("{}");
        verifyDataTypesWhenEmptyRequestBody(response);
        verifyHeaders(response);
    }

    @Test(dataProvider = DataProviderNames.INCORRECT_KEYS, dataProviderClass = UserDataProviders.class)
    @QaseId(13)
    @QaseTitle("Creating a user with incorrect keys in the request body")
    @Description("Creating a user with incorrect keys in the request body")
    public void incorrectKeyInRequestBody(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestResponseBody(response.getBody().asString());
        verifyHeaders(response);
    }

    @Test
    @QaseId(14)
    @QaseTitle("Creating a user with malformed request body")
    @Description("Creating a user with malformed request body")
    public void malformedRequestBody() {

        String JSON = """
                {
                    "name": "morpheus",
                    "salary": ,
                }
                """;

        Response response = sendRequest(JSON);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestResponseBody(response.getBody().asString());
        verifyHeaders(response);
    }
}
