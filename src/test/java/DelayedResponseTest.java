import base.ListBaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.support.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

@Epic("E2E")
@Feature("Delayed response")
public class DelayedResponseTest extends ListBaseTest {

    private Response set() {

        return given()
                .get("/api/users?delay=3");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the users list can be retrieved with a delayed response")
    @Story("As an user, I want to be able to retrieve the list of users even with a delayed response")
    @Test
    public void check() {

        Response response = set();
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.DELAYED_RESPONSE);
        verifyDataTypesInResponse(response);
        verifyListOfUsers(response);
    }
}
