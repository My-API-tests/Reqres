package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.data.Register;
import org.data.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.data.SuiteTags.PAYLOAD;
import static org.hamcrest.CoreMatchers.*;

import org.data.Resource;
import org.data.SingleUser;

public class JSONDataTest extends BaseTest {


    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void getSingleUser() {

        SingleUser singleUser = new SingleUser(2, "janet.weaver@reqres.in", "Janet",
               "Weaver", "https://reqres.in/img/faces/2-image.jpg",
               "https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/2")
                .then()
                .body(

                     "data.id", is(singleUser.getId()),
                "data.email", is(singleUser.getEmail()),
                        "data.first_name", is(singleUser.getFirstName()),
                        "data.last_name", is(singleUser.getLastName()),
                        "data.avatar", is(singleUser.getAvatar()),
                        "support.url", is(singleUser.getUrl()),
                        "support.text", is(singleUser.getText())
                );
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void listResource() {

        Resource resource = new Resource(1, 6, 12, 2);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/unknown")
                .then()
                .body(

                     "page", is(resource.getPage()),
                "per_page", is(resource.getPerPage()),
                        "total", is(resource.getTotal()),
                        "total_pages", is(resource.getTotalPages())
                );
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void create() {

        User user = new User("Caroline", "organist");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .body(

                     "name", is(user.getName()),
                "job", is(user.getJob()),
                        "id", notNullValue(),
                        "createdAt", notNullValue()
                );
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void updatePUT() {

        User user = new User("John", "software tester");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put("/api/users/2")
                .then()
                .body(

                     "name", is(user.getName()),
                "job", is(user.getJob()),
                        "updatedAt", notNullValue()
                );
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void updatePATCH() {

        User user = new User("Andrew", "pianist");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .patch("api/users/3")
                .then()
                .body(

                     "name", is(user.getName()),
                "job", is(user.getJob()),
                        "updatedAt", notNullValue()
                );
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void register() {

        Register register = new Register("eve.holt@reqres.in", "pistol");

        given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("/api/register")
                .then()
                .body(

                        "email", notNullValue(),
                   "token", notNullValue()
                );
    }

    @Test
    @Tag(PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void login() {

        Register register = new Register("eve.holt@reqres.in", "cityslicka");

        given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("/api/login")
                .then()
                .body("token", notNullValue());
    }
}
