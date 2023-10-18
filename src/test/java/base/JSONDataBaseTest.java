package base;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.qa.pojo.BasePojo;
import org.qa.utils.Function;


public class JSONDataBaseTest extends BaseTest {

    protected <T> void check(Response response, Function<Response, BasePojo> function, T expected, boolean comparing) {

        BasePojo given = function.getObject(response);

        if (comparing) {

            Assertions.assertTrue(given.hasValidData(expected));

        } else {

            Assertions.assertTrue(given.hasNotNullValues());
        }
    }
}
