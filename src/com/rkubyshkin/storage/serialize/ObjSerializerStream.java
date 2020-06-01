package com.rkubyshkin.storage.serialize;

import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;
import java.io.*;

public class ObjSerializerStream implements SerializerStream {

    @Override
    public void doWrite(Person r, OutputStream outStream) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(outStream)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Person doRead(InputStream inStream) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(inStream)) {
            return (Person) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("error person read", null, e);
        }
    }
}
