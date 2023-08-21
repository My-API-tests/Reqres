package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.data.Register;
import org.data.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import static io.restassured.RestAssured.given;
import static org.data.SuiteTags.PAYLOAD;


public class JSONStructureTest extends BaseTest {

    private void helper1(String url, InputStream schema) {

        assert schema != null;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    public <T> void helper2(Method method, String url, T body, InputStream schema) {

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(method, url)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }


    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void listUsers() {

        InputStream schema = getClass().getClassLoader().getResourceAsStream("list-users-json-schema.json");

        helper1("/api/users?page=2", schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void listResource() {

        InputStream schema = getClass().getClassLoader().getResourceAsStream("list-users-json-schema.json");

        helper1("/api/unknown", schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void singleUser() {

        InputStream schema = getClass().getClassLoader().getResourceAsStream("single-user-json-schema.json");

        helper1("/api/users/2", schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void singleResource() {

        InputStream schema = getClass().getClassLoader().getResourceAsStream("single-user-json-schema.json");

        helper1("/api/unknown/2", schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void create() {

        InputStream schema = getClass().getClassLoader().getResourceAsStream("create-json-schema.json");
        User user = new User("Pawel", "organist");

        helper2(Method.POST, "/api/users", user, schema);
    }



    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void updatePUT() {

        User user = new User("Andy", "builder");
        InputStream schema = getClass().getClassLoader().getResourceAsStream("create-json-schema.json");

        helper2(Method.PUT, "/api/users/3", user, schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void updatePATCH() {

        User user = new User("Maria", "geologist");
        InputStream schema = getClass().getClassLoader().getResourceAsStream("create-json-schema.json");

        helper2(Method.PATCH, "/api/users/4", user, schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void register() {

        Register register = new Register("eve.holt@reqres.in", "pistol");
        InputStream schema = getClass().getClassLoader().getResourceAsStream("register-json-schema.json");

        helper2(Method.POST, "/api/register", register, schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void login() {

        Register register = new Register("eve.holt@reqres.in", "cityslicka");
        InputStream schema = getClass().getClassLoader().getResourceAsStream("login-json-schema.json");

        helper2(Method.POST, "/api/login", register, schema);
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The structure of the json file follows the schema")
    public void delayedResponse() {

        InputStream schema = getClass().getClassLoader().getResourceAsStream("delayed-response-schema.json");

        helper1("/api/users?delay=3", schema);
    }
}
