package com.rkubyshkin.storage.serialize;

import com.rkubyshkin.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializerStream {
    void doWrite(Person r, OutputStream outStream) throws IOException;
    Person doRead(InputStream inStream) throws IOException;
}
