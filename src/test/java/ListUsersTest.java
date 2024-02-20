import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.qa.utils.JSONSchemas;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isA;

@Epic("E2E")
@Feature("List users")
public class ListUsersTest extends BaseTest {

    private Response set(String id) {

        return given()
                .get("/api/users?page=" + id);
    }

    @Step("Verify {page, per_page, total, total_pages} data types")
    private void verifyDataTypesInResponse(Response response) {

        response
                .then()
                .body("page", isA(Integer.class))
                .body("per_page", isA(Integer.class))
                .body("total", isA(Integer.class))
                .body("total_pages", isA(Integer.class));
    }

    @Step("Verify list of users")
    private void verifyListOfUsers(Response response) {

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        Assert.assertTrue(jsonArray.length() > 0);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user list can be retrieved using the correct user ID")
    @Story("As an user, I want to be able to retrieve the list of users using the correct user ID")
    @Test
    public void correctId() {

        Response response = set("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.LIST_USERS);
        verifyDataTypesInResponse(response);
        verifyListOfUsers(response);
    }

    @Description("Verify that an error message appears when an incorrect user ID is provided")
    @Story("As an user, I want to see an error message when I provide an incorrect user ID")
    @Test
    public void incorrectId() {

        Response response = set("^%&*");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
    }
}
