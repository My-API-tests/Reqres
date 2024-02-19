package base;

import com.google.common.collect.ImmutableMap;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.testdataloader.TestdataLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseTest {

    @Parameters({"fileName"})
    @BeforeSuite
    public void init(@Optional("noFile") String fileName) throws IOException, ParseException {

        RestAssured.baseURI = "https://reqres.in/";
        setAllureEnvironment();
        TestdataLoader.loadJsonSchemas();

        if (!fileName.equals("noFile")) {
            TestdataLoader.loadBodies(fileName);
        }
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

    protected void checkHeaders(Response response) {

        List<String> expected = new ArrayList<>(List.of(JSONDataTransformer.getHeaders()));
        List<String> given = response.headers().asList().stream().map(Header::getName).toList();

        Assert.assertTrue(given.containsAll(expected), "No complete headers");
    }
}
