package negativeTests.validInput;

import base.PerformanceSanityBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

import static org.qa.constans.SuiteTags.VALIDATE_STATUS_CODE;

public class PerformanceSanityTest extends PerformanceSanityBaseTest {

    @Test
    public void GET_singleUser() {

        singleUser("150");
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void GET_singleResource() {

        singleResource("2000");
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_registerNotDefinedUser() {

        register(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_registerMissingEmailOrUsername() {

        register(new RegisterBody("", "pistol"));
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_registerMissingPassword() {

        register(new RegisterBody("eve.holt@reqres.in", ""));
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_loginIncorrectUsernameCase() {

        login(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_loginMissingEmailOrUsername() {

        login(new RegisterBody("", "pistol"));
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 200")
    public void POST_loginMissingPassword() {

        login(new RegisterBody("eve.holt@reqres.in", ""));
    }
}
