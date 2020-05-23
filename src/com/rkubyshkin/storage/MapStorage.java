package com.rkubyshkin.storage;

import com.rkubyshkin.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    HashMap<Resume, String> mapStorage = new HashMap<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {

    }

    @Override
    protected Object getSearchKey(String uid) {
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
