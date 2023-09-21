package basicPositiveTests;

import base.JSONDataBaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.pojo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;
import org.qa.constans.RegisterBodies;
import org.qa.constans.SingleUserBodies;
import org.qa.constans.UserBodies;
import static org.qa.constans.SuiteTags.VALIDATE_PAYLOAD;


public class JSONDataTest extends JSONDataBaseTest {

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void GET_singleUser() {

        check(getResponse(Method.GET, "/api/users/2", SingleUserBodies.bodies.get(0)),
             (Response r)-> r.body().as(SingleUserResponseBody.class),
              SingleUserBodies.bodies.get(0), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void POST_create() {

        check(getResponse(Method.POST, "/api/users", UserBodies.bodies.get(0)),
              (Response r)-> r.body().as(CreatedUserResponseBody.class),
               UserBodies.bodies.get(0), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void PUT_update() {

        check(getResponse(Method.PUT, "/api/users/1", UserBodies.bodies.get(1)),
             (Response r)-> r.body().as(UpdatedUserResponseBody.class),
              UserBodies.bodies.get(1), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void PATCH_update() {

        check(getResponse(Method.PATCH, "/api/users/1", UserBodies.bodies.get(1)),
             (Response r)->r.body().as(UpdatedUserResponseBody.class),
              UserBodies.bodies.get(1), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void POST_register() {

        check(getResponse(Method.POST, "/api/register", RegisterBodies.bodies.get(0)),
                (Response r)->r.body().as(RegisterSuccessfulResponseBody.class),
                RegisterBodies.bodies.get(0), false);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void POST_login() {

        check(getResponse(Method.POST, "/api/login", RegisterBodies.bodies.get(1)),
                (Response r)->r.body().as(LoginSuccessfulResponseBody.class),
                RegisterBodies.bodies.get(1), false);
    }
}
