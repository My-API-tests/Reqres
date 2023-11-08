package destructivetests.malformeddata;

import base.BaseTest;
import io.restassured.http.Method;
import io.restassured.path.xml.XmlPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.InputStream;

public class ResponseBodyTest extends BaseTest {

    private void check(Method method, String url, InputStream inputStream) {

        XmlPath xmlPath = new XmlPath(

            XmlPath.CompatibilityMode.HTML,
            getResponse(method, url, inputStream).getBody().asString()
        );

        Assert.assertEquals(xmlPath.getString("html.head.title"), "Error");
        Assert.assertEquals(xmlPath.getString("html.body.pre"), "Bad Request");
    }

    @Test
    public void POST_create() {

        check(Method.POST, "api/users/", getClass().getClassLoader().getResourceAsStream("create-malformed-data.json"));
    }

    @Test
    public void PATCH_update() {

        check(Method.PATCH, "/api/users/3", getClass().getClassLoader().getResourceAsStream("update-malformed-data.json"));
    }

    @Test
    public void PUT_update() {

        check(Method.PUT, "/api/users/3", getClass().getClassLoader().getResourceAsStream("update-malformed-data.json"));
    }

    @Test
    public void POST_login() {

        check(Method.POST, "/api/login", getClass().getClassLoader().getResourceAsStream("register-malformed-data.json"));
    }

    @Test
    public void POST_register() {

        check(Method.POST, "/api/register", getClass().getClassLoader().getResourceAsStream("register-malformed-data.json"));
    }
}
