package com.rkubyshkin.storage;

import com.rkubyshkin.storage.serialize.XmlSerializerStream;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new AbstractPathStorage(DIR_STORAGE.getAbsolutePath(), new XmlSerializerStream()) {
        });
    }
}