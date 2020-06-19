package com.rkubyshkin.storage;

import com.rkubyshkin.Config;
import com.rkubyshkin.storage.serialize.XmlSerializerStream;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(new Config().getDbUrl(), new Config().getDbUser(), new Config().getDbPassword()));
    }
}