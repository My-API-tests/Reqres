package org.data;

import java.util.Arrays;
import java.util.List;

public class Headers {

    public static List<Header> headers1 = Arrays.asList(

            new Header("Content-Type", "application/json; charset=utf-8"),
            new Header("Connection", "keep-alive"),
            new Header("Cache-Control", "max-age=14400"),
            new Header("X-Powered-By", "Express"),
            new Header("Access-Control-Allow-Origin", "*")
    );

    public static List<Header> headers2 = Arrays.asList(

            new Header("Content-Type", "application/json; charset=utf-8"),
            new Header("Connection", "keep-alive"),
            new Header("X-Powered-By", "Express"),
            new Header("Access-Control-Allow-Origin", "*")
    );

    public static List<Header> headers3 = Arrays.asList(

            new Header("Content-Type", "application/json; charset=utf-8"),
            new Header("Connection", "keep-alive"),
            new Header("X-Powered-By", "Express"),
            new Header("Access-Control-Allow-Origin", "*")
    );
}
