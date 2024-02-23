import base.BaseTest;
import io.qameta.allure.*;
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
import static org.hamcrest.Matchers.isA;

@Epic("E2E")
@Feature("Create")
public class CreateUserTest extends BaseTest {

    private Response set(JSONObject body) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("/api/users/");
    }

    @Step("Verify {name, job, id, createdAt} data types")
    private void verifyDataTypesInResponse(Response response) {

        response
                .then()
                .assertThat()
                .body("name", isA(String.class))
                .body("job", isA(String.class))
                .body("id", isA(String.class))
                .body("createdAt", isA(String.class));
    }

    @Step("Verify {name, job, id, createdAt} values")
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user can be created using correct data")
    @Story("As an user, I want to be able to create an account using correct data")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void correct(JSONObject requestBody) {

        Response response = set(requestBody);
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.CREATE_USER);
        verifyDataTypesInResponse(response);
        verifyValuesInResponseWithRequest(response, requestBody);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user cannot be created when the \"name\" parameter is missing")
    @Story("As an user, I want to be able to create an account even if I do not provide a name")
    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    public void missingName(JSONObject requestBody) {

        Response response = set(requestBody);
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.MISSING_NAME);
        verifyJobValueInResponseWithRequest(response, requestBody);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user cannot be created when the \"job\" parameter is missing")
    @Story("As an user, I want to be able to create an account even if I do not provide a job")
    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    public void missingJob(JSONObject requestBody) {

        Response response = set(requestBody);
        verifyStatusCode(response, HttpStatus.SC_CREATED);
        verifyJSONSchema(response, JSONSchemas.MISSING_JOB);
        verifyNameValueInResponseWithRequest(response, requestBody);
    }
}
