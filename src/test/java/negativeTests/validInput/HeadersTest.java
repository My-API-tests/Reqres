package negativeTests.validInput;

import base.HeaderBaseTest;
import org.qa.bodies.RegisterBody;
import org.testng.annotations.Test;

public class HeadersTest extends HeaderBaseTest {

    @Test
    public void GET_singleUser() {

        singleUser("450");
    }

    @Test
    public void GET_singleResource() {

        singleResource("3000");
    }

    @Test
    public void POST_registerNotDefinedUser() {

        register(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test
    public void POST_registerMissingEmailOrUsername() {

        register(new RegisterBody("", "pistol"));
    }

    @Test
    public void POST_registerMissingPassword() {

        register(new RegisterBody("eve.holt@reqres.in", ""));
    }

    @Test
    public void POST_loginIncorrectUsername() {

        login(new RegisterBody("eve.holt@req.in", "pistol"));
    }

    @Test
    public void POST_loginMissingEmailOrUsername() {

        login(new RegisterBody("", "pistol"));
    }

    @Test
    public void POST_loginMissingPassword() {

        login(new RegisterBody("eve.holt@reqres.in", ""));
    }
}
