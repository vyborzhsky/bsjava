package com.rkubyshkin.storage;

import com.rkubyshkin.storage.serialize.GSONSerializerStream;

public class GSONPathStorageTest extends AbstractStorageTest {

    public GSONPathStorageTest() {
        super(new AbstractPathStorage(DIR_STORAGE.getAbsolutePath(), new GSONSerializerStream()) {
        });
    }
}