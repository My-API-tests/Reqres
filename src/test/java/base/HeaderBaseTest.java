package base;

import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.qa.bodies.Credentials;
import org.qa.constans.ExpectedHeaders;
import org.testng.Assert;
import java.util.List;


public class HeaderBaseTest extends BaseTest {

    protected void check(Response response) {

        List<Header> responseHeaders = response.getHeaders().asList();
        List<String> headerNames = responseHeaders.stream().map(Header::getName).toList();

        Assert.assertTrue(headerNames.contains(ExpectedHeaders.headers.get(0)));
    }

    protected void singleUser(String userNumber) {

        check(getResponse(Method.GET, "/api/users/" + userNumber));
    }

    protected void singleResource(String userNumber) {

        check(getResponse(Method.GET, "/api/unknown/" + userNumber));
    }

    protected void register(Credentials credentials) {

        check(getResponse(Method.POST, "/api/register" ,credentials));
    }

    protected void login(Credentials credentials) {

        check(getResponse(Method.POST, "/api/login", credentials));
    }
}
