package destructivetests.malformeddata;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.Method;
import io.restassured.path.xml.XmlPath;
import org.qa.extentreportsmanager.ExtentReportsManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.InputStream;

@Epic("E2E")
@Feature("Response body testing")
public class ResponseBodyTest extends BaseTest {

    private void check(Method method, String url, InputStream inputStream) {

        XmlPath xmlPath = new XmlPath(

            XmlPath.CompatibilityMode.HTML,
            getResponse(method, url, inputStream).getBody().asString()
        );

        Assert.assertEquals(xmlPath.getString("html.head.title"), "Error");
        Assert.assertEquals(xmlPath.getString("html.body.pre"), "Bad Request");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking whether the response body contains valid data when creating a new person using malformed data")
    @Story("Creating a new person using malformed data")
    public void POST_create() {

        ExtentReportsManager.setTestName("Creating a new person using malformed data");

        check(Method.POST, "api/users/", getClass().getClassLoader().getResourceAsStream("create-malformed-data.json"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking whether the response body contains valid data when updating a person via PATCH method using malformed data")
    @Story("Updating a person via PATCH method using malformed data")
    public void PATCH_update() {

        ExtentReportsManager.setTestName("Updating a person via PATCH method using malformed data");

        check(Method.PATCH, "/api/users/3", getClass().getClassLoader().getResourceAsStream("update-malformed-data.json"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking whether the response body contains valid data when updating a person via PUT method using malformed data")
    @Story("Updating a person via PUT method using malformed data")
    public void PUT_update() {

        ExtentReportsManager.setTestName("Updating a person via PUT method using malformed data");

        check(Method.PUT, "/api/users/3", getClass().getClassLoader().getResourceAsStream("update-malformed-data.json"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking whether the response body contains valid data when logging into an account using malformed data")
    @Story("Account login using malformed data")
    public void POST_login() {

        ExtentReportsManager.setTestName("Account login using malformed data");

        check(Method.POST, "/api/login", getClass().getClassLoader().getResourceAsStream("register-malformed-data.json"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking whether the response body contains valid data when registering an account using malformed data")
    @Story("Account registration using malformed data")
    public void POST_register() {

        ExtentReportsManager.setTestName("Account registration using malformed data");

        check(Method.POST, "/api/register", getClass().getClassLoader().getResourceAsStream("register-malformed-data.json"));
    }
}
