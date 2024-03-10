package org.qa.testdataloader;

import java.io.*;

import lombok.Getter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestdataLoader {

    private static final String SCHEMAS_PATH = "./src/test/resources/testdata/schemas/schemas.json";
    private static final String HEADERS_PATH = "./src/test/resources/testdata/headers/headers.json";
    private static final String BODIES_PATH = "./src/test/resources/testdata/bodies/";

    @Getter
    private static String bodySource;
    @Getter
    private static String headersSource;
    @Getter
    private static String schemasSource;

    public static void loadJsonSchemas() throws IOException, ParseException {

        Reader fileReader = new FileReader(SCHEMAS_PATH);
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(fileReader);
        schemasSource = object.toString();
    }

    public static void loadHeaders() throws IOException, ParseException {

        Reader fileReader = new FileReader(HEADERS_PATH);
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(fileReader);
        headersSource = object.toString();
    }

    public static void loadBodies(String fileName) throws IOException, ParseException {

        Reader fileReader = new FileReader(BODIES_PATH + fileName);
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(fileReader);
        bodySource = object.toString();
    }
}
