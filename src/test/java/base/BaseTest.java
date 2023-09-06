package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeClass
    public static void init() {

        RestAssured.baseURI = "https://reqres.in/";
    }

    protected Response getResponse(Method method, String url) {

        RequestSpecification requestSpecification = given();

        return requestSpecification.contentType(ContentType.JSON).request(method, url);
    }

    protected<T> Response getResponse(Method method, String url, T body) {

        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON).body(body);

        return requestSpecification.request(method, url);
    }
}
