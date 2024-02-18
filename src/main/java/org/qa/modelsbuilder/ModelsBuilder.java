package org.qa.modelsbuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.qa.testdataloader.TestdataLoader;

import java.io.IOException;
import java.util.stream.IntStream;

public class ModelsBuilder {
    public static JSONObject[] getUserBodies(String key) throws IOException {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getBodySource());
        JSONArray jsonArray = jsonObject.getJSONArray(key);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> new JSONObject(jsonArray.getJSONObject(i).toString()))
                .toArray(JSONObject[]::new);
    }

    public static String getJsonSchema(String key) {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getSchemasSource());

        return jsonObject.getJSONObject(key).toString();
    }

    public static String[] getHeaders() {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getSchemasSource());
        JSONArray jsonArray = jsonObject.getJSONArray("headers");

        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getString)
                .toArray(String[]::new);
    }
}
