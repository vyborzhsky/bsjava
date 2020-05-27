package com.rkubyshkin.storage;

import com.rkubyshkin.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected Resume doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        mapStorage.put(r.getUid(), r);
    }

    @Override
    protected String getSearchKey(String uid) {
        for (Map.Entry<String, Resume> entry : mapStorage.entrySet()) {
            if (entry.getKey().equals(uid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        mapStorage.replace(searchKey,r);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected List<Resume> doGetAllSorted() {
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
