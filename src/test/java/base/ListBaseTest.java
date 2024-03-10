package base;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.isA;

public class ListBaseTest extends BaseTest {

    @io.qameta.allure.Step("Verify {page, per_page, total, total_pages} data types")
    @io.qase.api.annotation.Step("Verify {page, per_page, total, total_pages} data types")
    protected void verifyDataTypesInResponse(Response response) {

        response
                .then()
                .body("page", isA(Integer.class))
                .body("per_page", isA(Integer.class))
                .body("total", isA(Integer.class))
                .body("total_pages", isA(Integer.class));
    }

    @io.qameta.allure.Step("Verify list of users")
    @io.qase.api.annotation.Step("Verify list of users")
    protected void verifyListOfUsers(Response response) {

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        Assert.assertTrue(jsonArray.length() > 0);
    }
}
