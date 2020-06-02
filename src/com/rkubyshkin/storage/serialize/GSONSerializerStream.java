package com.rkubyshkin.storage.serialize;

import com.rkubyshkin.model.Person;
import com.rkubyshkin.util.GsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GSONSerializerStream implements  SerializerStream {
    @Override
    public void doWrite(Person r, OutputStream outStream) throws IOException {
        try (Writer w = new OutputStreamWriter(outStream, StandardCharsets.UTF_8)) {
            GsonParser.write(r, w);
        }
    }

    @Override
    public Person doRead(InputStream inStream) throws IOException {
        try (Reader r = new InputStreamReader(inStream, StandardCharsets.UTF_8)) {
            return GsonParser.read(r, Person.class);
        }
    }
}
