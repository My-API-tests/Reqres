package negativeTests.validInput;

import base.HeaderBaseTest;
import io.qameta.allure.*;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.utils.TestType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.qa.extentreportsmanager.ExtentReportsManager;

@Epic("E2E")
@Feature("Testing headers")
public class HeadersTest extends HeaderBaseTest {

    @BeforeSuite
    public void setTestType() {

        TestType.setType("negative");
    }

    @Test(priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when trying to receive a single non-existent user")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single user");

        singleUser("450");
    }

    @Test(priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that HTTPS headers are as expected when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single resource");

        singleResource("3000");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        ExtentReportsManager.setTestName("Attempting to register with undefined user");

        register(RegisterCredentials.notDefinedUser());
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to register without email or username")
    @Story("Attempting to register without email or username")
    public void POST_registerMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Attempting to register without email or username");

        register(RegisterCredentials.withoutEmailOrUsername());
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to register without password")
    @Story("Attempting to register without password")
    public void POST_registerMissingPassword() {

        ExtentReportsManager.setTestName("Attempting to register without password");

        register(RegisterCredentials.withoutPassword());
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to login with an incorrect username")
    @Story("Attempting to login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        ExtentReportsManager.setTestName("Attempting to login with an incorrect username");

        login(LoginCredentials.incorrectUsername());
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to login without email or username")
    @Story("Attempting to login without email or username")
    public void POST_loginMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Attempting to login without email or username");

        login(LoginCredentials.withoutEmailOrUsername());
    }

    @Test(priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checking that HTTPS headers are as expected when trying to login without password")
    @Story("Attempting to login without password")
    public void POST_loginMissingPassword() {

        ExtentReportsManager.setTestName("Attempting to login without password");

        login(LoginCredentials.withoutPassword());
    }
}
