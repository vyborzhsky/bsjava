package com.rkubyshkin.storage;

import com.rkubyshkin.model.Person;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage<Integer> {
    private List<Person> list = new ArrayList<>();

    @Override
    protected Person doGet(Integer searchKey) {
        return list.get(searchKey);

    }

    @Override
    protected void doSave(Person r, Integer searchKey) {
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
    protected void doUpdate(Person r, Integer searchKey) {
        list.set(searchKey,r);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove((searchKey).intValue());

    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected List<Person> doGetAllSorted() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}
