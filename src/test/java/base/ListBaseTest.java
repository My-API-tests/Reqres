package base;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

public class ListBaseTest extends BaseTest {

    @io.qameta.allure.Step("Verify the <page> data type")
    @io.qase.api.annotation.Step("Verify the <page> data type")
    protected void verifyPageDataType(Response response) {

        checkDataType(response, "page", Integer.class);
    }

    @io.qameta.allure.Step("Verify the <per_page> data type")
    @io.qase.api.annotation.Step("Verify the <per_page> data type")
    protected void verifyPerPageDataType(Response response) {

        checkDataType(response, "per_page", Integer.class);
    }

    @io.qameta.allure.Step("Verify the <total> data type")
    @io.qase.api.annotation.Step("Verify the <total> data type")
    protected void verifyTotalDataType(Response response) {

        checkDataType(response, "total", Integer.class);
    }

    @io.qameta.allure.Step("Verify the <total_pages> data type")
    @io.qase.api.annotation.Step("Verify the <total_pages> data type")
    protected void verifyTotalPagesDataType(Response response) {

        checkDataType(response, "total_pages", Integer.class);
    }

    @io.qameta.allure.Step("Verify list of users")
    @io.qase.api.annotation.Step("Verify list of users")
    protected void verifyListOfUsers(Response response) {

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        Assert.assertTrue(jsonArray.length() > 0);
    }
}
