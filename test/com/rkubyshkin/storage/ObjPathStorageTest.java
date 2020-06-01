package com.rkubyshkin.storage;

import com.rkubyshkin.storage.serialize.ObjSerializerStream;

public class ObjPathStorageTest extends AbstractStorageTest {

    public ObjPathStorageTest() {
        super(new AbstractPathStorage(DIR_STORAGE.getAbsolutePath(), new ObjSerializerStream()) {
        });
    }
}