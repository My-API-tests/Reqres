import base.ListBaseTest;
import io.qameta.allure.*;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.support.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("E2E")
@Feature("List users")
public class ListUsersTest extends ListBaseTest {

    @io.qameta.allure.Step("Perform a GET request to https://reqres.in/api/users?page=<ID>, where <ID> represents a user ID number")
    @io.qase.api.annotation.Step("Perform a GET request to https://reqres.in/api/users?page=<ID>, where <ID> represents a user ID number")
    private Response set(String id) {

        return given()
                .get("/api/users?page=" + id);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @QaseId(3)
    @QaseTitle("Getting a list of users using a correct page ID")
    @Description("Getting a list of users using a correct page ID")
    public void correctPageId() {

        Response response = set("2");
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.LIST_USERS);
        verifyDataTypesInResponse(response);
        verifyListOfUsers(response);
        verifyHeaders(response);
    }

    @Test
    @QaseId(4)
    @QaseTitle("Getting a list of users using an incorrect page ID")
    @Description("Getting a list of users using an incorrect page ID")
    public void incorrectPageId() {

        Response response = set("^%&*");
        verifyStatusCode(response, HttpStatus.SC_NOT_FOUND);
        verifyJSONSchema(response, JSONSchemas.ERROR_RESPONSE);
        verifyHeaders(response);
    }
}
