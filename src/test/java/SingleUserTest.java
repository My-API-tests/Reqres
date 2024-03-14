import base.BaseTest;
import io.qameta.allure.*;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.support.JSONSchemas;
import org.qa.support.Patterns;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("E2E")
@Feature("Single user")
public class SingleUserTest extends BaseTest {

    @io.qameta.allure.Step("Perform a GET request to https://reqres.in/api/user/<ID>, where <ID> represents a user ID number")
    @io.qase.api.annotation.Step("Perform a GET request to https://reqres.in/api/user/<ID>, where <ID> represents a user ID number")
    private Response sendRequest(String user) {

        return given()
                .get("/api/users/" + user);
    }

    @io.qameta.allure.Step("Verify the <id> data type")
    @io.qase.api.annotation.Step("Verify the <id> data type")
    private void verifyIdDataType(Response response) {

        checkDataType(response, "data.id", Integer.class);
    }

    @io.qameta.allure.Step("Verify the <email> data type")
    @io.qase.api.annotation.Step("Verify the <email> data type")
    private void verifyEmailDataType(Response response) {

        checkDataType(response, "data.email", String.class);

    }

    @io.qameta.allure.Step("Verify the <first_name> data type")
    @io.qase.api.annotation.Step("Verify the <first_name> data type")
    private void verifyFirstNameDataType(Response response) {

        checkDataType(response, "data.first_name", String.class);
    }

    @io.qameta.allure.Step("Verify the <last_name> data type")
    @io.qase.api.annotation.Step("Verify the <last_name> data type")
    private void verifyLastNameDataType(Response response) {

        checkDataType(response, "data.last_name", String.class);

    }

    @io.qameta.allure.Step("Verify the <avatar> data type")
    @io.qase.api.annotation.Step("Verify the <avatar> data type")
    private void verifyAvatarDataType(Response response) {

        checkDataType(response, "data.avatar", String.class);
    }

    @io.qameta.allure.Step("Verify the <url> data type")
    @io.qase.api.annotation.Step("Verify the <url> data type")
    private void verifyUrlDataType(Response response) {

        checkDataType(response, "support.url", String.class);
    }

    @io.qameta.allure.Step("Verify the <text> data type")
    @io.qase.api.annotation.Step("Verify the <text> data type")
    private void verifyTextDataType(Response response) {

        checkDataType(response, "support.text", String.class);
    }

    @io.qameta.allure.Step("Verify the <email> format")
    @io.qase.api.annotation.Step("Verify the <email> format")
    private void verifyEmailFormat(Response response) {

        response.then().assertThat().body("data.email", matchesPattern(Patterns.EMAIL_FORMAT));
    }

    @io.qameta.allure.Step("Verify the <avatar> format")
    @io.qase.api.annotation.Step("Verify the <avatar> format")
    private void verifyAvatarFormat(Response response) {

        response.then().assertThat().body("data.avatar", matchesPattern(Patterns.AVATAR_FORMAT));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @QaseId(1)
    @QaseTitle("Getting a user data using an existing user ID")
    @Description("Getting a user data using an existing user ID")
    public void existingUserId() {

        Response response = sendRequest("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyIdDataType(response);
        verifyEmailDataType(response);
        verifyFirstNameDataType(response);
        verifyLastNameDataType(response);
        verifyAvatarDataType(response);
        verifyUrlDataType(response);
        verifyTextDataType(response);
        verifyEmailFormat(response);
        verifyAvatarFormat(response);
    }

    @Test
    @QaseId(2)
    @QaseTitle("Getting a user data using a non existing user ID")
    @Description("Getting a user data using a non existing user ID")
    void nonExistingUserId() {

        Response response = sendRequest("13");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.EMPTY_BODY);
    }
}
