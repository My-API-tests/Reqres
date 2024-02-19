import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.qa.dataproviders.UserDataProviders;
import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.DataProviderNames;
import org.qa.utils.JSONSchemas;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateTest extends BaseTest {

    private Response check(String id, int statusCode, JSONObject body, String jsonSchema) {

        return given().when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .put("/api/users/" + id)
                .then()
                .statusCode(statusCode)
                .body(JsonSchemaValidator.matchesJsonSchema(ModelsBuilder.getJsonSchema(jsonSchema)))
                .extract().response();
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void correct(JSONObject jsonObject) {

        Response response = check("2", HttpStatus.SC_OK, jsonObject, JSONSchemas.UPDATE_USER);

        response.then()
                .assertThat()
                .body("name", equalTo(jsonObject.getString("name")))
                .body("job", equalTo(jsonObject.getString("job")));
    }

    @Test(dataProvider = DataProviderNames.WITHOUT_NAME, dataProviderClass = UserDataProviders.class)
    public void withoutName(JSONObject jsonObject) {

        Response response = check("2", HttpStatus.SC_OK, jsonObject, JSONSchemas.UPDATE_USER);

        response.then()
                .assertThat()
                .body("job", equalTo(jsonObject.getString("job")));
    }

    @Test(dataProvider = DataProviderNames.WITHOUT_JOB, dataProviderClass = UserDataProviders.class)
    public void withoutJob(JSONObject jsonObject) {

        Response response = check("2", HttpStatus.SC_OK, jsonObject, JSONSchemas.UPDATE_USER);

        response.then()
                .assertThat()
                .body("name", equalTo(jsonObject.getString("name")));
    }

    @Test(dataProvider = DataProviderNames.CORRECT, dataProviderClass = UserDataProviders.class)
    public void incorrectId(JSONObject jsonObject) {

        Response response = check("10000", HttpStatus.SC_NOT_FOUND, jsonObject, JSONSchemas.EMPTY_BODY);
    }

    @Test
    public void malformedJSON() {

        String invalid = "{ \"name\": {\"$gt\": \"\"}, \"job\": {\"$ne\": \"\"}";

        String responseHTML = given()
                .contentType(ContentType.JSON)
                .body(invalid)
                .put("/api/users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Document document = Jsoup.parse(responseHTML);
        String preContent = document.select("pre").text();

        Assert.assertEquals(preContent, "Bad Request");
    }
}
