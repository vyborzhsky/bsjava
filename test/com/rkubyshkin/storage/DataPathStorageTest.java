package com.rkubyshkin.storage;

import com.rkubyshkin.storage.serialize.DataSerializerStream;
import com.rkubyshkin.storage.serialize.ObjSerializerStream;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new AbstractPathStorage(DIR_STORAGE.getAbsolutePath(), new DataSerializerStream()) {
        });
    }
}