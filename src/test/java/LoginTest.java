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

    private Response sendRequest(String requestBody) {

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

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.LOGIN);
        verifyTokenDataType(response);
        verifyTokenValueInResponseWithRequest(response);
    }

    @Test(dataProvider = DataProviderNames.INCORRECT_EMAIL, dataProviderClass = RegisterDataProviders.class)
    @QaseId(27)
    @QaseTitle("Login using an incorrect email")
    @Description("Login using an incorrect email")
    public void incorrectEmail(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataType(response);
        verifyErrorMessage(response, "user not found");
    }

    @Test(dataProvider = DataProviderNames.INCORRECT_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    @QaseId(28)
    @QaseTitle("Login using an incorrect password")
    @Description("Login using an incorrect password")
    public void incorrectPassword(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataType(response);
        verifyErrorMessage(response, "user not found");
    }

    @Test(dataProvider = DataProviderNames.MISSING_EMAIL, dataProviderClass = RegisterDataProviders.class)
    @QaseId(29)
    @QaseTitle("Login missing email")
    @Description("Login missing email")
    public void missingEmail(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataType(response);
        verifyErrorMessage(response, "Missing email or username");
    }

    @Test(dataProvider = DataProviderNames.MISSING_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    @QaseId(30)
    @QaseTitle("Login missing password")
    @Description("Login missing password")
    public void missingPassword(JSONObject requestBody) {

        Response response = sendRequest(requestBody.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataType(response);
        verifyErrorMessage(response, "Missing password");
    }

    @Test
    @QaseId(31)
    @QaseTitle("Login with empty request body")
    @Description("Login with empty request body")
    public void emptyRequestBody() {

        Response response = sendRequest("{}");
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataType(response);
        verifyErrorMessage(response, "Missing email or username");
    }

    @Test
    @QaseId(32)
    @QaseTitle("Login with malformed request body")
    @Description("Login with malformed request body")
    public void malformedJSON() {

        String malformedJSON = "{" + "  \"email\": \"example@example.com\"," +
                         "  \"password\": \"password123\"" +
                         "  \"role\": \"admin\"";

        Response response = sendRequest(malformedJSON);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestMessage(response.body().asString());
    }
}
