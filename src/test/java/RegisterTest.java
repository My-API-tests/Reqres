import base.BaseTest;
import io.qameta.allure.*;
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

@Epic("E2E")
@Feature("Register")
public class RegisterTest extends BaseTest {

    private Response set(String responseBody) {

        return given()
                .contentType(ContentType.JSON)
                .body(responseBody)
                .post("/api/register");
    }

    @Step("Verify {id, token} data types")
    private void verifyDataTypesInResponse(Response response) {

        response
                .then()
                .assertThat()
                .body("id", isA(Integer.class))
                .body("token", isA(String.class));
    }

    @Description("Verify that a user can be registered using correct credentials")
    @Story("As a user, I want to be able to register successfully using correct credentials")
    @Test(dataProvider = DataProviderNames.USER_NOT_DEFINED, dataProviderClass = RegisterDataProviders.class)
    public void correct(JSONObject body) {

        Response response = set(body.toString());
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.REGISTER);
        verifyDataTypesInResponse(response);
    }

    @Description("Verify that a new user cannot register if the user is not defined")
    @Story("As a user, I want to see an error message when I am an undefined user")
    @Test(dataProvider = DataProviderNames.USER_NOT_DEFINED, dataProviderClass = RegisterDataProviders.class)
    public void userNotDefined(JSONObject body) {

        Response response = set(body.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Only defined users succeed registration");
    }

    @Description("Verify that a new user cannot register when an email is missing")
    @Story("As a user, I want to see an error message if I do not provide an email during registration")
    @Test(dataProvider = DataProviderNames.MISSING_EMAIL, dataProviderClass = RegisterDataProviders.class)
    public void missingEmail(JSONObject body) {

        Response response = set(body.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Missing email or username");
    }

    @Description("Verify that a new user cannot register when an email is missing")
    @Story("As a user, I want to see an error message if I do not provide an email during registration")
    @Test(dataProvider = DataProviderNames.MISSING_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    public void missingPassword(JSONObject body) {

        Response response = set(body.toString());
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyErrorDataTypeInResponse(response);
        verifyErrorValueInResponseWithRequest(response, "Missing password");
    }

    @Description("Verify that an error message appears when sending a malformed JSON request body")
    @Story("As a user, I want to see an error message when I provide an incorrect request body format")
    @Test
    public void malformedJSON() {

        String invalid = "{" + "  \"email\": \"example@example.com\"," + "  \"password\": \"password123\"";

        Response response = set(invalid);
        verifyStatusCode(response, HttpStatus.SC_BAD_REQUEST);
        verifyBadRequestResponseBody(response.getBody().asString());
    }
}
