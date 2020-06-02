package com.rkubyshkin.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

public class XmlParser {
    private final Marshaller mrshlr;
    private final Unmarshaller unmrshlr;

    public XmlParser(Class... boundClasses) {
        try {
            JAXBContext context = JAXBContext.newInstance(boundClasses);

            mrshlr = context.createMarshaller();
            mrshlr.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mrshlr.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            unmrshlr = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public  <X> X unmarshall(Reader reader) {
        try {
            return (X) unmrshlr.unmarshal(reader);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public  void marshall(Object instance, Writer writer) {
        try {
            mrshlr.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}
