package negativetests;

import base.PerformanceSanityBaseTest;
import io.qameta.allure.*;
import org.qa.factories.LoginCredentials;
import org.qa.factories.RegisterCredentials;
import org.qa.utils.TestType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.qa.extentreportsmanager.ExtentReportsManager;


@Epic("E2E")
@Feature("Performance sanity tests")
public class PerformanceSanityTest extends PerformanceSanityBaseTest {

    @BeforeSuite
    public void setTestType() {

        TestType.setType("negative");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to receive a single non-existent user")
    @Story("Attempting to obtain a non-existent single user")
    public void GET_singleUser() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single user");

        singleUser("500");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to receive a single non-existent resource")
    @Story("Attempting to obtain a non-existent single resource")
    public void GET_singleResource() {

        ExtentReportsManager.setTestName("Attempting to obtain a non-existent single resource");

        singleResource("2000");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to register with an undefined user")
    @Story("Attempting to register with undefined user")
    public void POST_registerNotDefinedUser() {

        ExtentReportsManager.setTestName("Attempting to register with undefined user");

        register(RegisterCredentials.notDefinedUser());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to register without email or username")
    @Story("Account registration without email or username")
    public void POST_registerMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Account registration without email or username");

        register(RegisterCredentials.withoutEmailOrUsername());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to to register without password")
    @Story("Account registration without password")
    public void POST_registerMissingPassword() {

        ExtentReportsManager.setTestName("Account registration without password");

        register(RegisterCredentials.withoutPassword());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to login with an incorrect username")
    @Story("Login with an incorrect username")
    public void POST_loginIncorrectUsername() {

        ExtentReportsManager.setTestName("Login with an incorrect username");

        login(LoginCredentials.incorrectUsername());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to login without email or username")
    @Story("Login without username")
    public void POST_loginMissingEmailOrUsername() {

        ExtentReportsManager.setTestName("Login without username");

        login(LoginCredentials.withoutEmailOrUsername());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking that response time is less than or equal to 5000 ms when trying to login without password")
    @Story("Login without password")
    public void POST_loginMissingPassword() {

        ExtentReportsManager.setTestName("Login without password");

        login(LoginCredentials.withoutPassword());
    }
}
