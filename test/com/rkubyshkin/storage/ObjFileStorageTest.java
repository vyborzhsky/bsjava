package com.rkubyshkin.storage;

import com.rkubyshkin.storage.serialize.ObjSerializerStream;

public class ObjFileStorageTest extends AbstractStorageTest {

    public ObjFileStorageTest() {
        super(new AbstractFileStorage(DIR_STORAGE, new ObjSerializerStream()) {
        });
    }
}