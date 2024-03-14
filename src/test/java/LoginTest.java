import base.BaseTest;
import io.qameta.allure.*;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.qa.dataproviders.RegisterDataProviders;
import org.qa.support.DataProviderNames;
import org.qa.support.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;

@Epic("E2E")
@Feature("Login")
public class LoginTest extends BaseTest {

    private Response set(String requestBody) {

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/login");
    }

    @io.qameta.allure.Step("Verify the <token> data type")
    @io.qase.api.annotation.Step("Verify the <token> data type")
    private void verifyTokenDataType(Response response) {

        response.then().assertThat().body("token", isA(String.class));
    }

    @io.qameta.allure.Step("Verify the <token> value")
    @io.qase.api.annotation.Step("Verify the <token> value")
    private void verifyTokenValueInResponseWithRequest(Response response) {

        response.then().assertThat().body("token", not(""));
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = RegisterDataProviders.class)
    @QaseId(26)
    @QaseTitle("Login using correct credentials")
    @Description("Login using correct credentials")
    public void correct(JSONObject requestBody) {

        Response response = set(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.LOGIN);
        verifyTokenDataType(response);
        verifyTokenValueInResponseWithRequest(response);
    }

    @Test(dataProvider = DataProviderNames.INCORRECT_EMAIL, dataProviderClass = RegisterDataProviders.class)
    @QaseId(27)
    @QaseTitle("Login using an incorrect email")
    @Description("Login using an incorrect credentials")
    public void incorrectEmail(JSONObject requestBody) {

        Response response = set(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "user not found");
    }

    @Description("Verify that a new user cannot log in using an incorrect password")
    @Story("As an user, I want to see an error message when I provide an incorrect password during login")
    @Test(dataProvider = DataProviderNames.INCORRECT_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    public void incorrectPassword(JSONObject requestBody) {

        Response response = set(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "user not found");
    }

    @Description("Verify that a new user cannot log in when an email is missing")
    @Story("As a user, I want to see an error message if I do not provide an email during login")
    @Test(dataProvider = DataProviderNames.MISSING_EMAIL, dataProviderClass = RegisterDataProviders.class)
    public void missingEmail(JSONObject requestBody) {

        Response response = set(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Missing email or username");
    }

    @Description("Verify that a new user cannot log in when a password is missing")
    @Story("As a user, I want to see an error message if I do not provide a password during login")
    @Test(dataProvider = DataProviderNames.MISSING_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    public void missingPassword(JSONObject requestBody) {

        Response response = set(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Missing password");
    }

    @Description("Verify that an error message appears when sending a malformed JSON request body")
    @Story("As a user, I want to see an error message when I provide an incorrect request body format")
    @Test
    public void malformedJSON() {

        String body = "{" + "  \"email\": \"example@example.com\"," +
                         "  \"password\": \"password123\"" +
                         "  \"role\": \"admin\"";

        Response response = set(body);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestResponseBody(response.body().asString());
    }
}
