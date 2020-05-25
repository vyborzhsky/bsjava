package com.rkubyshkin.storage;

import com.rkubyshkin.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<Object, Resume> mapStorage = new HashMap<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapStorage.put(r.getUid(), r);
    }

    @Override
    protected Object getSearchKey(String uid) {
        for (Map.Entry<Object, Resume> entry : mapStorage.entrySet()) {
            if (entry.getKey().equals(uid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapStorage.replace(searchKey,r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        TreeMap<Object, Resume> sorted = new TreeMap<>();
        List<Resume> resumes = new ArrayList<>();
        sorted.putAll(mapStorage);
        resumes.addAll(sorted.values());
        return resumes;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
