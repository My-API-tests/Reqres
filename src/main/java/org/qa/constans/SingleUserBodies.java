package org.qa.constans;

import org.qa.bodies.Data;
import org.qa.pojo.SingleUserResponseBody;
import org.qa.bodies.Support;

import java.util.List;

public class SingleUserBodies {

    public static final List<SingleUserResponseBody> bodies = List.of(

            new SingleUserResponseBody(new Data(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg"), new Support("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!"))
    );
}
