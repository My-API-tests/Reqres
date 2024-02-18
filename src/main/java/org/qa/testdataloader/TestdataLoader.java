package org.qa.testdataloader;

import java.io.*;

import lombok.Getter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TestdataLoader {

    private static final String schemasPath = "./src/test/resources/testdata/schemas/schemas.json";
    private static final String bodiesPath = "./src/test/resources/testdata/bodies/";
    @Getter
    private static String bodySource;
    @Getter
    private static String schemasSource;

    public static void loadJsonSchemas() throws IOException, ParseException {

        Reader fileReader = new FileReader(schemasPath);
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(fileReader);
        schemasSource = object.toString();
    }

    public static void loadBodies(String fileName) throws IOException, ParseException {

        Reader fileReader = new FileReader(bodiesPath + fileName);
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(fileReader);
        bodySource = object.toString();
    }
}
