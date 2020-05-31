package com.rkubyshkin.storage;

import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;

import java.io.*;

public class ObjStreamStorage extends AbstractFileStorage  {

    protected ObjStreamStorage(File directoryOfResumes) {
        super(directoryOfResumes);
    }

    @Override
    protected void doWrite(Person r, OutputStream outStream) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(outStream)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Person doRead(InputStream inStream) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(inStream)) {
            return (Person) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("error person read", null, e);
        }
    }
}
