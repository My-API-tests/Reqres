import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.qa.dataproviders.UserDataProviders;
import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.DataProviderNames;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {

    private Response check(JSONObject body) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("/api/users/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body(JsonSchemaValidator.matchesJsonSchema(ModelsBuilder.getJsonSchema(JSONSchemas.CREATE_USER)))
                .extract().response();
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void correct(JSONObject jsonObject) {

        Response response = check(jsonObject);

        response.then()
                .assertThat()
                .body("name", equalTo(jsonObject.getString("name")))
                .body("job", equalTo(jsonObject.getString("job")));

        checkHeaders(response);
    }

    @Test(dataProvider = DataProviderNames.WITHOUT_NAME, dataProviderClass = UserDataProviders.class)
    public void withoutName(JSONObject jsonObject) {

        Response response = check(jsonObject);

        response.then()
                .assertThat()
                .body("job", equalTo(jsonObject.getString("job")));

        checkHeaders(response);
    }

    @Test(dataProvider = DataProviderNames.WITHOUT_JOB, dataProviderClass = UserDataProviders.class)
    public void withoutJob(JSONObject jsonObject) {

        Response response = check(jsonObject);

        response.then()
                .assertThat()
                .body("name", equalTo(jsonObject.getString("name")));

        checkHeaders(response);
    }
}
