import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.qa.dataproviders.RegisterDataProviders;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.DataProviderNames;
import org.qa.utils.JSONSchemas;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("E2E")
@Feature("Register")
public class RegisterTest extends BaseTest {

    private Response check(int statusCode, JSONObject body, String jsonSchemaKey) {

        return given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("/api/register")
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(jsonSchemaKey)))
                .extract().response();
    }

    @Description("Verify that a user can be registered using correct credentials")
    @Story("As a user, I want to be able to register successfully using correct credentials")
    @Test(dataProvider = DataProviderNames.USER_NOT_DEFINED, dataProviderClass = RegisterDataProviders.class)
    public void correct(JSONObject body) {

        Response response = check(HttpStatus.SC_OK, body, JSONSchemas.REGISTER);
        System.out.println(response.body().prettyPrint());
    }

    @Description("Verify that a new user cannot register if the user is not defined")
    @Story("As a user, I want to see an error message when I am an undefined user")
    @Test(dataProvider = DataProviderNames.USER_NOT_DEFINED, dataProviderClass = RegisterDataProviders.class)
    public void userNotDefined(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that a new user cannot register when an email is missing")
    @Story("As a user, I want to see an error message if I do not provide an email during registration")
    @Test(dataProvider = DataProviderNames.MISSING_EMAIL, dataProviderClass = RegisterDataProviders.class)
    public void missingEmail(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that a new user cannot register when an email is missing")
    @Story("As a user, I want to see an error message if I do not provide an email during registration")
    @Test(dataProvider = DataProviderNames.MISSING_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    public void missingPassword(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that an error message appears when sending a malformed JSON request body")
    @Story("As a user, I want to see an error message when I provide an incorrect request body format")
    @Test
    public void malformedJSON() {

        String invalid = "{" + "  \"email\": \"example@example.com\"," + "  \"password\": \"password123\"";

        String responseHTML = given()
                .contentType(ContentType.JSON)
                .body(invalid)
                .post("/api/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body().asString();

        Document document = Jsoup.parse(responseHTML);
        String preContent = document.select("prev").text();

        Assert.assertEquals(preContent, "Bad Request");
    }
}
