package com.rkubyshkin.storage;
import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
        protected static final int STORAGE_LIMIT = 10000;
        protected Resume[] storage = new Resume[STORAGE_LIMIT];
        protected int size = 0;

        @Override
        protected boolean isExist(Integer index) {
            return index >= 0;
        }

        public int size() {
            return size;
        }

        @Override
        protected void doUpdate(Resume r, Integer index) {
            storage[index] = r;
        }

        @Override
        public void doDelete(Integer index) {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }

        @Override
        protected void doSave(Resume r, Integer index) {
            if (size == STORAGE_LIMIT) {
                throw new StorageException("Storage overflow", r.getUid());
            } else {
                insertElement(r, index);
                size++;
            }
        }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else {
                resumes.add(storage[i]);
            }
        }
        return resumes;
    }

    @Override
        public Resume doGet(Integer index) {
            return storage[index];
        }

        public void clear() {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        }

        protected abstract Integer getSearchKey(String uid);

        protected abstract void insertElement(Resume r, int index);

        protected abstract void fillDeletedElement(int index);

    }
