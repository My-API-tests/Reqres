package basicPositiveTests;

import base.JSONDataBaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.factories.SingleUserBodyFactory;
import org.qa.factories.UserBodyFactory;
import org.qa.pojo.*;
import org.testng.annotations.Test;
import org.qa.constans.UserBodies;


@Epic("E2E")
@Feature("Testing JSON data")
public class JSONDataTest extends JSONDataBaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when receiving a single user")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        check(getResponse(Method.GET, "/api/users/2", SingleUserBodyFactory.correctBody()),
             (Response r)-> r.body().as(SingleUserResponseBody.class),
              SingleUserBodyFactory.correctBody(), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when creating a new user")
    @Story("Creating a new user")
    public void POST_create() {

        check(getResponse(Method.POST, "/api/users", UserBodyFactory.correct()),
              (Response r)-> r.body().as(CreatedUserResponseBody.class),
               UserBodies.bodies.get(0), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when updating a person via PUT")
    @Story("User update using the PUT method")
    public void PUT_update() {

        check(getResponse(Method.PUT, "/api/users/1", UserBodyFactory.toUpdate_PUT()),
             (Response r)-> r.body().as(UpdatedUserResponseBody.class),
              UserBodyFactory.toUpdate_PUT(), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when updating a person via PATCH")
    @Story("User update using the PATCH method")
    public void PATCH_update() {

        check(getResponse(Method.PATCH, "/api/users/1", UserBodyFactory.toUpdate_PATCH()),
             (Response r)->r.body().as(UpdatedUserResponseBody.class),
              UserBodyFactory.toUpdate_PATCH(), true);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when registering an account")
    @Story("Account registration")
    public void POST_register() {

        check(getResponse(Method.POST, "/api/register", RegisterCredentials.correct()),
                (Response r)->r.body().as(RegisterSuccessfulResponseBody.class),
                RegisterCredentials.correct(), false);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that the response structure has a correct data when logging into an account")
    @Story("Account login")
    public void POST_login() {

        check(getResponse(Method.POST, "/api/login", LoginCredentials.correct()),
                (Response r)->r.body().as(LoginSuccessfulResponseBody.class),
                LoginCredentials.correct(), false);
    }
}
