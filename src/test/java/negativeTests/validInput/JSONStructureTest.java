package negativeTests.validInput;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

import java.io.InputStream;

public class JSONStructureTest extends BaseTest {

    private void check(Response response, String JSONSchema) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JSONSchema);

        assert inputStream != null;

        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(inputStream));

        //System.out.println(response.prettyPrint());
    }

    @Test
    public void getSingleUser() {

        check(getResponse(Method.GET, "/api/users/250"), "negative-valid-input-get-single-user.json");
    }

    @Test
    public void getSingleResource() {

        check(getResponse(Method.GET, "/api/unknown/2000"),"negative-valid-input-get-single-resource.json");
    }

    @Test
    public void registerNotDefinedUser() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@req.in", "pistol")),"negative-valid-input-register-not-defined.json");
    }

    @Test
    public void registerMissingEmailOrUsername() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("", "pistol")), "negative-valid-input-register-not-defined.json");
    }

    @Test
    public void registerMissingPassword() {

        check(getResponse(Method.POST, "/api/register", new RegisterBody("eve.holt@reqres.in", "")), "negative-valid-input-register-not-defined.json");
    }

    @Test
    @DisplayName("Should return status code 200")
    public void loginIncorrectUsername() {

        check(getResponse(Method.POST, "/api/login", new RegisterBody("eve.holt@req.in", "pistol")), "negative-valid-input-register-not-defined.json");
    }
}
