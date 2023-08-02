package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.data.SuiteTags.PAYLOAD;

import org.data.Resource;
import org.data.SingleUser;

public class ValidatePayload extends BaseTest {

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


}
