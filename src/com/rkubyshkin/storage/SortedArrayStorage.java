package com.rkubyshkin.storage;

import com.rkubyshkin.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uid) {
        Resume searchKey = new Resume();
        searchKey.setUid(uid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uid) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
