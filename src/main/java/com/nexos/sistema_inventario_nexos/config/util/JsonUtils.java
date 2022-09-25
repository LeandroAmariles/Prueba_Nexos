package com.nexos.sistema_inventario_nexos.config.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    public static String objectToJson(Object arg) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(arg);
    }

    public static <T> T jsonToObject(String json, Class<T> arg) throws IOException {
        return OBJECT_MAPPER.readValue(json, arg);
    }


    public static <C extends Collection<T>, T> C jsonToCollection(String json, Class<C> arg0, Class<T> arg1) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(arg0, arg1));
    }


    public static <T> T jsonToGenericObject(String json, TypeReference<T> valueTypeRef) throws IOException {
        return OBJECT_MAPPER.readValue(json, valueTypeRef);
    }

    public static String getNode(String json, String node) throws IOException {
        return OBJECT_MAPPER.readTree(json).get(node).toString();
    }
}
