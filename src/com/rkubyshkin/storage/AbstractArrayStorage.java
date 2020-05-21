package com.rkubyshkin.storage;
import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void delete(String uid) {
        int index = getIndex(uid);
        if(index < 0) {
            throw new NotExistStorageException(uid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUid());
        if (index > 0) {
            throw new ExistStorageException(r.getUid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUid());
        if (index == -1) {
            throw new NotExistStorageException(r.getUid());
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage,0, size);
    }

    public Resume get(String uid) {
        int index = getIndex(uid);
        if(index < 0) {
            throw new NotExistStorageException(uid);
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getIndex(String uid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
