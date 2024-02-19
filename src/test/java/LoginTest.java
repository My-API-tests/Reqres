import base.BaseTest;
import io.qameta.allure.Description;
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

public class LoginTest extends BaseTest {

    private Response check(int statusCode, JSONObject body, String jsonSchemaKey) {

        return given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("/api/login")
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(jsonSchemaKey)))
                .extract().response();
    }

    @Description("Verify that a new user can be logged in using correct credentials")
    @Story("As a user, I want to be able to log in successfully using correct credentials")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = RegisterDataProviders.class)
    public void correct(JSONObject body) {

        Response response = check(HttpStatus.SC_OK, body, JSONSchemas.LOGIN);
    }

    @Description("Verify that a new user cannot log in using an incorrect email")
    @Story("As an user, I want to see an error message when I provide an incorrect email during login")
    @Test(dataProvider = DataProviderNames.INCORRECT_EMAIL, dataProviderClass = RegisterDataProviders.class)
    public void incorrectEmail(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that a new user cannot log in using an incorrect password")
    @Story("As an user, I want to see an error message when I provide an incorrect password during login")
    @Test(dataProvider = DataProviderNames.INCORRECT_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    public void incorrectPassword(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that a new user cannot log in when an email is missing")
    @Story("As a user, I want to see an error message if I do not provide an email during login")
    @Test(dataProvider = DataProviderNames.MISSING_EMAIL, dataProviderClass = RegisterDataProviders.class)
    public void missingEmail(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that a new user cannot log in when a password is missing")
    @Story("As a user, I want to see an error message if I do not provide a password during login")
    @Test(dataProvider = DataProviderNames.MISSING_PASSWORD, dataProviderClass = RegisterDataProviders.class)
    public void missingPassword(JSONObject body) {

        Response response = check(HttpStatus.SC_BAD_REQUEST, body, JSONSchemas.ERROR_RESPONSE);
    }

    @Description("Verify that an error message appears when sending a malformed JSON request body")
    @Story("As a user, I want to see an error message when I provide an incorrect request body format")
    @Test
    public void malformedJSON() {

        String body = "{" + "  \"email\": \"example@example.com\"," +
                         "  \"password\": \"password123\"" +
                         "  \"role\": \"admin\"";

        String responseHTML = given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/api/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body().asString();

        Document document = Jsoup.parse(responseHTML);
        String preContent = document.select("pre").text();

        Assert.assertEquals(preContent, "Bad Request");
    }
}
