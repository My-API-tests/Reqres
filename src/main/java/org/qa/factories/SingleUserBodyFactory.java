package org.qa.factories;

import org.qa.bodies.Data;
import org.qa.bodies.Support;
import org.qa.pojo.SingleUserResponseBody;

public class SingleUserBodyFactory {

    public static SingleUserResponseBody correctBody() {

        return new SingleUserResponseBody(new Data(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg"), new Support("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
