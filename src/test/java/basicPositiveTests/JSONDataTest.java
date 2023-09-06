package basicPositiveTests;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.qa.pojo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;
import org.qa.constans.RegisterBodies;
import org.qa.constans.SingleUserBodies;
import org.qa.constans.UserBodies;
import org.qa.utils.Function;
import static org.qa.constans.SuiteTags.VALIDATE_PAYLOAD;


public class JSONDataTest extends BaseTest {

    public <T extends BasePojo, X> void check(Response response, Function<Response, T> function, X expected, boolean comparing) {

        T given = function.getObject(response);

        if (comparing) {

            Assertions.assertTrue(given.hasValidData(expected));

        } else {

            Assertions.assertTrue(given.hasNotNullValues());
        }
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void getSingleUser() {

        check(getResponse(Method.GET, "/api/users/2", SingleUserBodies.bodies.get(0)),
             (Response r)-> r.body().as(SingleUserResponseBody.class),
              SingleUserBodies.bodies.get(0), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void create() {

        check(getResponse(Method.POST, "/api/users", UserBodies.bodies.get(0)),
              (Response r)-> r.body().as(CreatedUserResponseBody.class),
               UserBodies.bodies.get(0), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void updatePUT() {

        check(getResponse(Method.PUT, "/api/users/1", UserBodies.bodies.get(1)),
             (Response r)-> r.body().as(UpdatedUserResponseBody.class),
              UserBodies.bodies.get(1), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void updatePATCH() {

        check(getResponse(Method.PATCH, "/api/users/1", UserBodies.bodies.get(1)),
             (Response r)->r.body().as(UpdatedUserResponseBody.class),
              UserBodies.bodies.get(1), true);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void register() {

        check(getResponse(Method.POST, "/api/register", RegisterBodies.bodies.get(0)),
                (Response r)->r.body().as(RegisterSuccessfulResponseBody.class),
                RegisterBodies.bodies.get(0), false);
    }

    @Test
    @Tag(VALIDATE_PAYLOAD)
    @DisplayName("The response structure should follow the data model")
    public void login() {

        check(getResponse(Method.POST, "/api/login", RegisterBodies.bodies.get(1)),
                (Response r)->r.body().as(LoginSuccessfulResponseBody.class),
                RegisterBodies.bodies.get(1), false);
    }
}
