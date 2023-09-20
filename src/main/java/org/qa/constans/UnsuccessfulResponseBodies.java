package org.qa.constans;

import org.qa.pojo.BasePojo;
import org.qa.pojo.RegisterLoginUnsuccessfulResponseBody;

import java.util.List;

public class UnsuccessfulResponseBodies {

    public static final List<BasePojo> bodies = List.of(

        new RegisterLoginUnsuccessfulResponseBody("Note: Only defined users succeed registration")
    );
}
