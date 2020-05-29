package com.rkubyshkin.storage;
import com.rkubyshkin.model.Person;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Person r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer getSearchKey(String uid) {
        for (int j = 0; j < size; j++) {
            if (storage[j].getUid().equals(uid)) {
                return j;
            }
        }
        return -1;
    }
}
