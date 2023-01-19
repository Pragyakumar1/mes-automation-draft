package com.twist.twistautomation.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import java.util.Map;

public class JsonUtils {

    public static String updateJSONString(String oldJsonString, String jsonPath, Object value) {
        Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
                .mappingProvider(new JacksonMappingProvider()).build();
        DocumentContext doc = com.jayway.jsonpath.JsonPath.using(configuration).parse(oldJsonString);
        String newJson = doc.set(jsonPath, value).jsonString();
        if (newJson.contains("//")) {
            newJson = newJson.replaceAll("//", "");
        }
        return newJson;
    }
    public static String updateJSONString(String oldJsonString, Map<String, Object> map) {
        String newJson = "";
        Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
                .mappingProvider(new JacksonMappingProvider()).build();
        DocumentContext doc = com.jayway.jsonpath.JsonPath.using(configuration).parse(oldJsonString);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            newJson = doc.set(entry.getKey(), entry.getValue()).jsonString();
        }
        return newJson;
    }
    public static String deleteJSONNode(String jsonString, String jPath) {
        DocumentContext doc = JsonPath.parse(jsonString);
        String newJSon = doc.delete(jPath).jsonString();
        return newJSon;
    }

    public static String addJSONNode(String jsonString, String parent,String jPath, Object value) {
        DocumentContext doc = JsonPath.parse(jsonString);
        String newJSon = doc.put(parent, jPath, value).jsonString();
        return newJSon;
    }

    public static Object extractJSON(String jsonString, String jPath) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        Object value = JsonPath.read(document, jPath);
        //System.out.println(value.getClass());
        return value;
    }
}
