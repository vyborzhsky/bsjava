package com.rkubyshkin.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rkubyshkin.model.Unit;

import java.io.Reader;
import java.io.Writer;

public class GsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Unit.class, new AdapterGsonUnit<>())
            .create();

    public static  <X> X read(Reader rd, Class<X> cl) {
        return GSON.fromJson(rd,cl);
    }

    public static <X> void write(X obj, Writer wr) {
        GSON.toJson(obj, wr);
    }
}
