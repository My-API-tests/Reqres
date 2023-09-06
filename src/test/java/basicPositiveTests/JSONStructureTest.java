package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;
import org.qa.bodies.UserBody;
import reportsManager.ExtentReportsManager;

import java.io.InputStream;
import static org.qa.constans.SuiteTags.VALIDATE_PAYLOAD;


public class JSONStructureTest extends BaseTest {

    private void check(Response response, String JsonSchema) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JsonSchema);

        assert inputStream != null;

        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(inputStream));
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void listUsers() {

        ExtentReportsManager.setTestName("List users");

        check(getResponse(Method.GET, "/api/users?page=2"), "list-users-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void listResource() {

        ExtentReportsManager.setTestName("List <Resource>");

        check(getResponse(Method.GET, "/api/unknown"), "list-users-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void singleUser() {

        ExtentReportsManager.setTestName("Get single user");

        check(getResponse(Method.GET, "/api/users/2"), "single-user-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void singleResource() {

        ExtentReportsManager.setTestName("Single <Resource>");

        check(getResponse(Method.GET, "/api/unknown/2"), "single-user-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void create() {

        ExtentReportsManager.setTestName("Create");

        check(getResponse(Method.POST, "/api/users", new UserBody("Pawel", "Organist")), "create-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void updatePUT() {

        ExtentReportsManager.setTestName("Updating using PUT");

        check(getResponse(Method.PUT, "/api/users/3", new UserBody("Andy", "Builder")), "create-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void updatePATCH() {

        ExtentReportsManager.setTestName("Updating using PATCH");

        check(getResponse(Method.PATCH, "/api/users/4", new UserBody("Maria", "Geologist")), "create-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void register() {

        ExtentReportsManager.setTestName("Register");

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")), "register-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void login() {

        ExtentReportsManager.setTestName("Login");

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "cityslicka")), "login-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void delayedResponse() {

        ExtentReportsManager.setTestName("Delayed response");

        check(getResponse(Method.GET, "/api/users?delay=3"), "delayed-response-schema.json");
    }
}
