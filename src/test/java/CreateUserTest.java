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

    @io.qameta.allure.Step("Verify the <id> data type")
    @io.qase.api.annotation.Step("Verify the <id> data type")
    private void verifyIdDataType(Response response) {

        checkDataType(response, "id", String.class);
    }

    @io.qameta.allure.Step("Verify the <createdAt> data type")
    @io.qase.api.annotation.Step("Verify the <createdAt> data type")
    private void verifyCreatedAtDataType(Response response) {

        checkDataType(response, "createdAt", String.class);
    }

    @io.qameta.allure.Step("Verify the <name> value")
    @io.qase.api.annotation.Step("Verify the <name> value")
    private void verifyNameValue(Response response, JSONObject requestBody) {

        response.then().assertThat().body("name", equalTo(requestBody.getString("name")));
    }

    @io.qameta.allure.Step("Verify the {job} value")
    @io.qase.api.annotation.Step("Verify the {job} value")
    private void verifyJobValue(Response response, JSONObject requestBody) {

        response.then().assertThat().body("job", equalTo(requestBody.getString("job")));
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    @QaseId(9)
    @QaseTitle("Creating a user using correct credentials")
    @Description("Creating a user using correct credentials")
    public void correct(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.CREATE_USER);
        verifyNameDataType(response);
        verifyJobDataType(response);
        verifyIdDataType(response);
        verifyCreatedAtDataType(response);
        verifyNameValue(response, requestBody);
        verifyJobValue(response, requestBody);
        verifyHeaders(response);
    }

    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    @QaseId(10)
    @QaseTitle("Creating a user missing the {name}")
    @Description("Creating a user missing the {name}")
    public void missingName(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.CREATE_USER_MISSING_NAME);
        verifyJobDataType(response);
        verifyIdDataType(response);
        verifyCreatedAtDataType(response);
        verifyNameValue(response, requestBody);
        verifyJobValue(response, requestBody);
        verifyHeaders(response);
    }

    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    @QaseId(11)
    @QaseTitle("Creating a user missing the {job}")
    @Description("Creating a user missing the {job}")
    public void missingJob(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.CREATE_USER_MISSING_JOB);
        verifyNameDataType(response);
        verifyIdDataType(response);
        verifyCreatedAtDataType(response);
        verifyNameValue(response, requestBody);
        verifyJobValue(response, requestBody);
        verifyHeaders(response);
    }

    @Test
    @QaseId(12)
    @QaseTitle("Creating a user with empty request body")
    @Description("Creating a user with empty request body")
    public void emptyRequestBody() {

        Response response = sendRequest("{}");
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.CREATE_USER_EMPTY_BODY);
        verifyIdDataType(response);
        verifyCreatedAtDataType(response);
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

        String JSON = "{\"name\": \"morpheus\", \"salary\": ,}";

        Response response = sendRequest(JSON);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestResponseBody(response.getBody().asString());
        verifyHeaders(response);
    }
}
