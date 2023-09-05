package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.qa.bodies.RegisterBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.qa.bodies.UserBody;
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

        check(getResponse(Method.GET, "/api/users?page=2"), "list-users-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void listResource() {

        check(getResponse(Method.GET, "/api/unknown"), "list-users-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void singleUser() {

        check(getResponse(Method.GET, "/api/users/2"), "single-user-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void singleResource() {

        check(getResponse(Method.GET, "/api/unknown/2"), "single-user-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void create() {

        check(getResponse(Method.POST, "/api/users", new UserBody("Pawel", "Organist")), "create-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void updatePUT() {

        check(getResponse(Method.PUT, "/api/users/3", new UserBody("Andy", "Builder")), "create-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void updatePATCH() {

        check(getResponse(Method.PATCH, "/api/users/4", new UserBody("Maria", "Geologist")), "create-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void register() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "pistol")), "register-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void login() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@reqres.in", "cityslicka")), "login-json-schema.json");
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"), "delayed-response-schema.json");
    }
}
