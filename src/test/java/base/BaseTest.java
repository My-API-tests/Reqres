package base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "https://reqres.in/";
    }
}
