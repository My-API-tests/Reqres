package basicPositiveTests;

import base.PerformanceSanityBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.Method;
import org.qa.bodies.RegisterBody;
import org.qa.bodies.UserBody;
import org.testng.annotations.Test;


public class PerformanceSanityTest extends PerformanceSanityBaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving single users")
    @Story("Acquiring a single user")
    public void GET_singleUser() {

        singleUser("4");
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving a list of resources")
    @Story("Getting the list of resource")
    public void GET_listResource() {

        check(getResponse(Method.GET, "/api/unknown"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving a single resource")
    @Story("Obtaining one resource")
    public void GET_singleResource() {

        singleResource("2");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when receiving delayed response data")
    @Story("Retrieving data via delayed response")
    public void GET_delayedResponse() {

        check(getResponse(Method.GET, "/api/users?delay=3"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when creating a new person")
    @Story("Creating a new person")
    public void POST_create() {

        check(getResponse(Method.POST, "/api/users", new UserBody("John", "Medic")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when updating a person via PUT")
    @Story("Account update using the PUT method")
    public void PUT_update() {

        check(getResponse(Method.PUT, "/api/users5", new UserBody("Patrick", "Miner")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when updating a person via PATCH")
    @Story("Account update using the PATCH method")
    public void PATCH_update() {

        check(getResponse(Method.PATCH, "/api/users/4", new UserBody("Anna", "Cosmetic")));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when registering an account")
    @Story("Account registration")
    public void POST_register() {

        register(new RegisterBody("eve.holt@reqres.in", "pistol"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 500 ms when logging into an account")
    @Story("Account login")
    public void POST_login() {

        login(new RegisterBody("eve.holt@reqres.in", "pistol"));
    }
}
