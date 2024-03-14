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

@Epic("E2E")
@Feature("Register")
public class RegisterTest extends BaseTest {

    private Response sendRequest(String responseBody) {

        return given()
                .contentType(ContentType.JSON)
                .body(responseBody)
                .post("/api/register");
    }

    @io.qameta.allure.Step("Verify the <id> data type")
    @io.qase.api.annotation.Step("Verify the <id> data type")
    private void verifyIdDataType(Response response) {

        checkDataType(response, "id", Integer.class);
    }

    @io.qameta.allure.Step("Verify the <token> data type")
    @io.qase.api.annotation.Step("Verify the <token> data type")
    private void verifyTokenDataType(Response response) {

        checkDataType(response, "token", String.class);
    }

    @Test(dataProvider = DataProviderNames.USER_NOT_DEFINED, dataProviderClass = RegisterDataProviders.class)
    @QaseId(21)
    @QaseTitle("Register using correct credentials")
    @Description("Register using correct credentials")
    public void correct(JSONObject body) {

        Response response = sendRequest(body.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.REGISTER);
        verifyIdDataType(response);
        verifyTokenDataType(response);
    }

    @Test(dataProvider = DataProviderNames.USER_NOT_DEFINED, dataProviderClass = RegisterDataProviders.class)
    @QaseId(22)
    @QaseTitle("Register when a user is not defined")
    @Description("Register when a user is not defined")
    public void userNotDefined(JSONObject body) {

        Response response = sendRequest(body.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Only defined users succeed registration");
    }

    @Test(dataProvider = DataProviderNames.MISSING_EMAIL, dataProviderClass = RegisterDataProviders.class)
    @QaseId(23)
    @QaseTitle("Register missing email")
    @Description("Register missing email")
    public void missingEmail(JSONObject body) {

        Response response = sendRequest(body.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Missing email or username");
    }

    @Test(dataProvider = DataProviderNames.MISSING_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    @QaseId(24)
    @QaseTitle("Register missing password")
    @Description("Register missing password")
    public void missingPassword(JSONObject body) {

        Response response = sendRequest(body.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Missing password");
    }

    @Test
    @QaseId(25)
    @QaseTitle("Register with malformed request body")
    @Description("Register with malformed request body")
    public void malformedJSON() {

        String invalid = "{" + "  \"email\": \"example@example.com\"," + "  \"password\": \"password123\"";

        Response response = sendRequest(invalid);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestResponseBody(response.getBody().asString());
    }
}
