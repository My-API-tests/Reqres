package destructivetests.malformeddata;


import base.StatusCodeBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.qa.extentreportsmanager.ExtentReportsManager;
import org.testng.annotations.Test;
import java.io.InputStream;


public class StatusCodeTest extends StatusCodeBaseTest {


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when creating a new person using malformed data")
    @Story("Creating a new person using malformed data")
    public void POST_create() {

        ExtentReportsManager.setTestName("Creating a new person using malformed data");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("create-malformed-data.json");

        check(getResponse(Method.POST, "/api/users/", inputStream), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when updating a person via PATCH using malformed data")
    @Story("Account update using the PATCH method and malformed data")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("Account update using the PATCH method and malformed data");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("update-malformed-data.json");

        check(getResponse(Method.PATCH, "/api/users/3", inputStream), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when updating a person via PUT using malformed data")
    @Story("Account update using the PUT method and malformed data")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Account update using the PATCH method and malformed data");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("update-malformed-data.json");

        check(getResponse(Method.PUT, "/api/users/3", inputStream), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to login with malformed data")
    @Story("Login with malformed data")
    public void POST_login() {

        ExtentReportsManager.setTestName("Login with an incorrect username using malformed data");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("register-malformed-data.json");

        login(inputStream, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that status code is 400 when trying to register using malformed data")
    @Story("Account registration using malformed data")
    public void POST_register() {

        ExtentReportsManager.setTestName("Account registration using malformed data");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("register-malformed-data.json");

        register(inputStream, HttpStatus.SC_BAD_REQUEST);
    }
}
