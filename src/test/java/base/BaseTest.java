package base;

import com.google.common.collect.ImmutableMap;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseTest {

    @BeforeSuite
    public void init() {

        RestAssured.baseURI = "https://reqres.in/";
        setAllureEnvironment();
    }

    protected void setAllureEnvironment() {

        allureEnvironmentWriter(

                ImmutableMap.<String, String>builder()
                        .put("Available processors (core)", String.valueOf(Runtime.getRuntime().availableProcessors()))
                        .put("Maximum memory", String.valueOf(Runtime.getRuntime().maxMemory()))
                        .put("Total memory", String.valueOf(Runtime.getRuntime().totalMemory()))
                        .put("Free memory", String.valueOf(Runtime.getRuntime().freeMemory()))
                        .put("Available processors", String.valueOf(Runtime.getRuntime().availableProcessors()))
                        .put("System property", System.getProperty("user.dir"))
                        .put("Operating system", System.getProperty("os.name") + " " + System.getProperty("os.arch"))
                        .put("Java runtime version", System.getProperty("java.runtime.version"))
                        .put("URL", "https://requres.in/")
                        .build()
        );
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
