package base;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.isA;

public class ListBaseTest extends BaseTest {

    @Step("Verify {page, per_page, total, total_pages} data types")
    protected void verifyDataTypesInResponse(Response response) {

        response
                .then()
                .body("page", isA(Integer.class))
                .body("per_page", isA(Integer.class))
                .body("total", isA(Integer.class))
                .body("total_pages", isA(Integer.class));
    }

    @Step("Verify list of users")
    protected void verifyListOfUsers(Response response) {

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        Assert.assertTrue(jsonArray.length() > 0);
    }
}
