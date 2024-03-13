package org.qa.jsondatatransformer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.qa.support.DataProviderNames;
import org.qa.testdataloader.TestdataLoader;

import java.util.stream.IntStream;

public class JSONDataTransformer {
    public static JSONObject[] getUserData(String key) {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getBodySource());
        JSONArray jsonArray = jsonObject.getJSONArray(key);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> new JSONObject(jsonArray.getJSONObject(i).toString()))
                .toArray(JSONObject[]::new);
    }

    public static JSONObject[] getDataWithIncorrectKeys() {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getBodySource());
        JSONArray jsonArray = jsonObject.getJSONArray(DataProviderNames.INCORRECT_KEYS);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> new JSONObject(jsonArray.getJSONObject(i).toString()))
                .toArray(JSONObject[]::new);
    }

    public static String getJsonSchema(String key) {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getSchemasSource());

        return jsonObject.getJSONObject(key).toString();
    }

    public static String[] getHeaders() {

        JSONObject jsonObject = new JSONObject(TestdataLoader.getHeadersSource());
        JSONArray jsonArray = jsonObject.getJSONArray("headers");

        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getString)
                .toArray(String[]::new);
    }
}
