package com.rkubyshkin.storage;

public class ObjStreamStorageTest extends AbstractStorageTest {

    public ObjStreamStorageTest() {
        super(new ObjStreamStorage(DIR_STORAGE));
    }
}