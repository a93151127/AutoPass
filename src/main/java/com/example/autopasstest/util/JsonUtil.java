package com.example.autopasstest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
    public static <T> T jsonToObject(String jsonString, Class<T> obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 可以沒有 rootValue
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        // 可以單引號
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 可以有未知的屬性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper.readValue(jsonString, obj);
    }

    public static String objectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(obj);
    }
}
