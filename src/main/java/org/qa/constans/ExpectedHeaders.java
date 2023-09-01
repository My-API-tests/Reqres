package org.qa.constans;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExpectedHeaders {

    public static final Map<String, String> headers = Map.of(

            "Content-Type", "application/json; charset=utf-8",
            "Connection", "keep-alive",
            "Cache-Control", "max-age=14400",
            "X-Powered-By", "Express",
            "Access-Control-Allow-Origin","*",
            "Date", DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))
    );
}
