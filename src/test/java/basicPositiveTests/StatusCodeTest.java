package basicPositiveTests;

import base.BaseTest;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.data.SuiteTags.STATUS_CODE;
import org.data.Register;
import org.data.User;

public class StatusCodeTest extends BaseTest {

    private void get(String URI) {
        given()
            .when()
            .get(URI)
            .then()
            .statusCode(HttpStatus.SC_OK);
    }

    private void post(String URI, Register register) {

        given()
            .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post(URI)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getListUsers() {

        get("/api/users?page=2");
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getSingleUser() {

        get("/api/users/3");
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getList() {

        get("/api/unknown");
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void getSingleResource() {

        get("/api/unknown/2");
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 201")
    public void create() {

        User user = new User("Pawel", "organist");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void updatePUT() {

        User user = new User("Dorotka", "helper");

        given()
            .contentType(ContentType.JSON)
            .body(user)
            .when()
            .put("/api/users/2")
            .then()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void updatePATCH() {

        User user = new User("Antosia", "child");

        given()
             .contentType(ContentType.JSON)
             .body(user)
             .when()
             .patch("/api/users/2")
             .then()
             .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void register() {

        Register register = new Register("eve.holt@reqres.in", "pistol");

        post("/api/register", register);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void login() {

        Register register = new Register("eve.holt@reqres.in", "cityslicka");

        post("/api/login", register);
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void delayedResponse() {

        get("/api/users?delay=3");
    }

    @Test
    @Tag(STATUS_CODE)
    @DisplayName("Should return status code 204")
    public void delete() {

        given()
            .when()
            .delete("/api/users/2")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
