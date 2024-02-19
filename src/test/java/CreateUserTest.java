import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.qa.dataproviders.UserDataProviders;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.DataProviderNames;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@Epic("E2E")
@Feature("Create")
public class CreateUserTest extends BaseTest {

    private Response check(JSONObject body) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("/api/users/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(JSONSchemas.CREATE_USER)))
                .extract().response();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user can be created using correct data")
    @Story("As an user, I want to be able to create an account using correct data")
    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void correct(JSONObject body) {

        Response response = check(body);

        response.then()
                .assertThat()
                .body("name", equalTo(body.getString("name")))
                .body("job", equalTo(body.getString("job")));

        checkHeaders(response);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user cannot be created when the \"name\" parameter is missing")
    @Story("As an user, I want to be able to create an account even if I do not provide a name")
    @Test(dataProvider = DataProviderNames.MISSING_NAME, dataProviderClass = UserDataProviders.class)
    public void missingName(JSONObject body) {

        Response response = check(body);

        response.then()
                .assertThat()
                .body("job", equalTo(body.getString("job")));

        checkHeaders(response);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user cannot be created when the \"job\" parameter is missing")
    @Story("As an user, I want to be able to create an account even if I do not provide a job")
    @Test(dataProvider = DataProviderNames.MISSING_JOB, dataProviderClass = UserDataProviders.class)
    public void missingJob(JSONObject body) {

        Response response = check(body);

        response.then()
                .assertThat()
                .body("name", equalTo(body.getString("name")));

        checkHeaders(response);
    }
}
