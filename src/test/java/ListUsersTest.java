import base.ListBaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.utils.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("E2E")
@Feature("List users")
public class ListUsersTest extends ListBaseTest {

    private Response set(String id) {

        return given()
                .get("/api/users?page=" + id);
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
