package com.rkubyshkin.storage;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUid);
    public void delete(String uid) {
        Object searchKey = getExistedSearchKey(uid);
        doDelete(searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUid());
        doSave(r, searchKey);
    }

    public void update (Resume r) {
        Object searchKey = getExistedSearchKey(r.getUid());
        doUpdate(r, searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = doGetAllSorted();
        Collections.sort(resumes, RESUME_COMPARATOR);
        return resumes;
    }

    public Resume get (String uid) {
        Object searchKey = getExistedSearchKey(uid);
        return doGet(searchKey);
    }

    private  Object getExistedSearchKey(String uid) {
        Object searchKey = getSearchKey(uid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uid);
        }
        return searchKey;
    }

    private  Object getNotExistedSearchKey(String uid) {
        Object searchKey = getSearchKey(uid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uid);
        }
        return searchKey;
    }
    protected abstract List<Resume> doGetAllSorted();

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Object getSearchKey(String uid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doDelete(Object searchKey);
}