package base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.testdataloader.TestdataLoader;
import org.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

public class BaseTest {

    @Parameters({"fileName"})
    @BeforeSuite
    public void init(@Optional("noFile") String fileName) throws IOException, ParseException {

        TestUtils.setAllureEnvironment();
        RestAssured.baseURI = "https://reqres.in/";
        TestdataLoader.loadJsonSchemas();

        if (!fileName.equals("noFile")) {
            TestdataLoader.loadBodies(fileName);
        }
    }

    @Step("Verify status code")
    protected void verifyStatusCode(Response response, int statusCode) {

        response
                .then()
                .statusCode(statusCode);
    }

    @Step("Verify JSON schema")
    protected void verifyJSONSchema(Response response, String jsonSchemaKey) {

        response
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(JSONDataTransformer.getJsonSchema(jsonSchemaKey)));
    }

    @Step("Verify the {error} data type")
    protected void verifyErrorDataTypeInResponse(Response response) {

        response
                .then()
                .assertThat()
                .body("error", isA(String.class));
    }

    @Step("Verify the {error} value")
    protected void verifyErrorValueInResponseWithRequest(Response response, String expectedMessage) {

        response
                .then()
                .assertThat()
                .body("error", equalTo(expectedMessage));
    }

    @Step("Verify {Bad Request} response body")
    protected void verifyBadRequestResponseBody(String responseHTML) {

        Document document = Jsoup.parse(responseHTML);
        String preContent = document.select("pre").text();

        Assert.assertEquals(preContent, "Bad Request");
    }

    @Step("Verify headers")
    protected void verifyHeaders(Response response) {

        List<String> expected = new ArrayList<>(List.of(JSONDataTransformer.getHeaders()));
        List<String> given = response.headers().asList().stream().map(Header::getName).toList();

        Assert.assertTrue(given.containsAll(expected), "No complete headers");
    }
}
