import base.ListBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qase.api.annotation.QaseId;
import io.qase.api.annotation.QaseTitle;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.qa.support.JSONSchemas;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListResourceTest extends ListBaseTest {

    @io.qameta.allure.Step("Perform a GET request to https://reqres.in/api/unknown")
    @io.qase.api.annotation.Step("Perform a GET request to https://reqres.in/api/unknown")
    private Response sendRequest() {

        return given()
                .get("/api/unknown");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @QaseId(7)
    @QaseTitle("Getting a list of resource")
    @Description("Getting a list of resource")
    public void correctPageId() {

        Response response = sendRequest();
        verifyStatusCode(response, HttpStatus.SC_OK);
        verifyJSONSchema(response, JSONSchemas.LIST_USERS);
        verifyPageDataType(response);
        verifyPerPageDataType(response);
        verifyTotalDataType(response);
        verifyTotalPagesDataType(response);
        verifyListOfUsers(response);
        verifyHeaders(response);
    }
}
