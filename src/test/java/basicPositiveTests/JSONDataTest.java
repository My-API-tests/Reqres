package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.qa.pojo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.qa.constans.RegisterBodies;
import org.qa.constans.SingleUserBodies;
import org.qa.constans.UserBodies;
import static org.qa.constans.SuiteTags.VALIDATE_PAYLOAD;


public class JSONDataTest extends BaseTest {


    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void getSingleUser() {

        SingleUserResponseBody singleUserResponseBody = SingleUserBodies.bodies.get(0);

        Response response = getResponse(Method.GET, "/api/users/2");
        SingleUserResponseBody result = response.body().as(SingleUserResponseBody.class);

        Assertions.assertEquals(singleUserResponseBody, result);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void listResource() {

    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void create() {

        Response response = getResponse(Method.POST, "/api/users", UserBodies.bodies.get(0));
        CreatedUserResponseBody given = response.body().as(CreatedUserResponseBody.class);

        Assertions.assertTrue(given.hasValidData(UserBodies.bodies.get(0)));
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void updatePUT() {

        Response response = getResponse(Method.PUT, "/api/users/1", UserBodies.bodies.get(1));
        UpdatedUserResponseBody given = response.body().as(UpdatedUserResponseBody.class);

        Assertions.assertTrue(given.hasValidData(UserBodies.bodies.get(1)));
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void updatePATCH() {

        Response response = getResponse(Method.PATCH, "/api/users/1", UserBodies.bodies.get(1));
        UpdatedUserResponseBody given = response.body().as(UpdatedUserResponseBody.class);

        Assertions.assertTrue(given.hasValidData(UserBodies.bodies.get(1)));
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void register() {

        Response response = getResponse(Method.POST, "/api/register", RegisterBodies.bodies.get(0));
        RegisterSuccessfulResponseBody given = response.body().as(RegisterSuccessfulResponseBody.class);

        Assertions.assertTrue(given.hasNotNullValues());
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void login() {

        Response response = getResponse(Method.POST, "/api/login", RegisterBodies.bodies.get(1));
        LoginSuccessfulResponseBody given = response.body().as(LoginSuccessfulResponseBody.class);

        Assertions.assertTrue(given.hasNotNullValues());
    }
}
