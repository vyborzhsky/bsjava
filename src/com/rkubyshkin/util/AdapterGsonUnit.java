package com.rkubyshkin.util;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AdapterGsonUnit<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        JsonPrimitive primitive = (JsonPrimitive) jsonObj.get(CLASSNAME);
        String className = primitive.getAsString();

        try {
            Class cl = Class.forName(className);
            return jsonDeserializationContext.deserialize(jsonObj.get(INSTANCE), cl);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject returnValues = new JsonObject();
        returnValues.addProperty(CLASSNAME,t.getClass().getName());
        JsonElement element = jsonSerializationContext.serialize(t);
        returnValues.add(INSTANCE, element);
        return returnValues;
    }
}
