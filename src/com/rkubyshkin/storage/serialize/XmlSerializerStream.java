package com.rkubyshkin.storage.serialize;

import com.rkubyshkin.model.*;
import com.rkubyshkin.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializerStream implements SerializerStream {
    XmlParser xmlParser;

    public XmlSerializerStream() {
        xmlParser = new XmlParser(Person.class, Structure.class, Link.class, StructureUnit.class,
                TextUnit.class, ListUnit.class, Structure.StructureListUnit.class);
    }

    @Override
    public void doWrite(Person p, OutputStream outputStream) throws IOException {
        try (Writer w = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(p,w);
        }
    }

    @Override
    public Person doRead(InputStream inputStream) throws IOException {
        try (Reader r = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
