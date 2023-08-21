package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.ContentType;
import  static io.restassured.RestAssured.given;
import static org.data.SuiteTags.VALIDATE_HEADERS;
import io.restassured.http.Method;
import org.data.Headers;
import org.data.Register;
import org.data.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class HeadersTest extends BaseTest {

    private void helper1(String url) {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .headers(
                            Headers.headers1.get(0).getName(), Headers.headers1.get(0).getValue(),
                            Headers.headers1.get(1).getName(), Headers.headers1.get(1).getValue(),
                            Headers.headers1.get(2).getName(), Headers.headers1.get(2).getValue(),
                            Headers.headers1.get(3).getName(), Headers.headers1.get(3).getValue(),
                            Headers.headers1.get(4).getName(), Headers.headers1.get(4).getValue()
                );
    }

    private <T> void helper2(Method method, String url, T body) {

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(method, url)
                .then()
                .assertThat()
                .headers(
                            Headers.headers2.get(0).getName(), Headers.headers2.get(0).getValue(),
                            Headers.headers2.get(1).getName(), Headers.headers2.get(1).getValue(),
                            Headers.headers2.get(2).getName(), Headers.headers2.get(2).getValue(),
                            Headers.headers2.get(3).getName(), Headers.headers2.get(3).getValue()
                );
    }

    private void helper3() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users?delay=3")
                .then()
                .assertThat()
                .headers(
                            Headers.headers3.get(0).getName(), Headers.headers3.get(0).getValue(),
                            Headers.headers3.get(1).getName(), Headers.headers3.get(1).getValue(),
                            Headers.headers3.get(2).getName(), Headers.headers3.get(2).getValue(),
                            Headers.headers3.get(3).getName(), Headers.headers3.get(3).getValue()
                );
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void getSingleUser() {

        helper1("/api/users/3");
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void listUsers() {

        helper1("/api/users?page=2");
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void listResource() {

        helper1("/api/unknown");
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void singleResource() {

        helper1("/api/unknown/2");
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void create() {

        User user = new User("Pawel", "organist");
        helper2(Method.POST, "/api/users", user);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void updatePUT() {

        User user = new User("Kate", "pianist");
        helper2(Method.PUT, "/api/users/1", user);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void updatePOST() {

        User user = new User("Carlos", "worker");
        helper2(Method.PATCH,"/api/users/4", user);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void register() {

        Register register = new Register("eve.holt@reqres.in", "pistol");
        helper2(Method.POST, "/api/register", register);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void login() {

        Register register = new Register("eve.holt@reqres.in", "cityslicka");
        helper2(Method.POST, "/api/login", register);
    }

    @Test
    @Tag(VALIDATE_HEADERS)
    @DisplayName("HTTPS headers are as expected")
    public void delayedResponse() {

        helper3();
    }
}
