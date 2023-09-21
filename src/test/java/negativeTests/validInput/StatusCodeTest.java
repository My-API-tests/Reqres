package negativeTests.validInput;

import base.StatusCodeBaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;
import static org.qa.constans.SuiteTags.VALIDATE_STATUS_CODE;

public class StatusCodeTest extends StatusCodeBaseTest {

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 404")
    public void GET_singleUser() {

        singleUser("150", HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 404")
    public void GET_singleResource() {

        singleResource("2000", HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 400")
    public void POST_registerNotDefinedUser() {

        register(new RegisterBody("eve.holt@req.in", "pistol"), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 400")
    public void POST_registerMissingEmailOrUsername() {

        register(new RegisterBody("", "pistol"), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 400")
    public void POST_registerMissingPassword() {

        register(new RegisterBody("eve.holt@reqres.in", ""), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 400")
    public void POST_loginIncorrectUsername() {

        login(new RegisterBody("eve.holt@req.in", "pistol"), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 400")
    public void POST_loginMissingEmailOrUsername() {

        login(new RegisterBody("", "pistol"), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag(VALIDATE_STATUS_CODE)
    @DisplayName("Should return status code 400")
    public void POST_loginMissingPassword() {

        login(new RegisterBody("eve.holt@reqres.in", ""), HttpStatus.SC_BAD_REQUEST);
    }
}
