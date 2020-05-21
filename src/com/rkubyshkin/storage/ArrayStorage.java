package com.rkubyshkin.storage;
import com.rkubyshkin.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uid) {
        for (int j = 0; j < size; j++) {
            if (storage[j].getUid().equals(uid)) {
                return j;
            }
        }
        return -1;
    }
}
