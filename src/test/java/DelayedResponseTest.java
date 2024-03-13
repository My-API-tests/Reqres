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
@Feature("Delayed response")
public class DelayedResponseTest extends ListBaseTest {


    @io.qameta.allure.Step("Perform a GET request to https://reqres.in/api/users=<D> where represents a delay ID number")
    @io.qase.api.annotation.Step("Perform a GET request to https://reqres.in/api/users=<D> where represents a delay ID number")
    private Response sendRequest() {

        return given()
                .get("/api/users?delay=2");
    }

    @Test
    @QaseId(8)
    @QaseTitle("Getting a list of users with a delayed response")
    @Description("Getting a list of users with a delayed response")
    public void check() {

        Response response = sendRequest();
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.DELAYED_RESPONSE);
        verifyDataTypesInResponse(response);
        verifyListOfUsers(response);
        verifyHeaders(response);
    }
}
