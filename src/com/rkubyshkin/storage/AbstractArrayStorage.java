package com.rkubyshkin.storage;
import com.rkubyshkin.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uid) {
        int index = getIndex(uid);
        if(index == -1) {
            System.out.println("RESUME" + uid + "NOT EXIST");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uid);

}
