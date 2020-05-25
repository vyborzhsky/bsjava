package com.rkubyshkin.storage;

import com.rkubyshkin.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);

    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected Integer getSearchKey(String uid) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUid().equals(uid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((Integer) searchKey,r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());

    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}
