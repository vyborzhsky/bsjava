package com.rkubyshkin.storage;

import com.rkubyshkin.model.Person;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Person> mapStorage = new HashMap<>();

    @Override
    protected Person doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected void doSave(Person r, String searchKey) {
        mapStorage.put(r.getUid(), r);
    }

    @Override
    protected String getSearchKey(String uid) {
        for (Map.Entry<String, Person> entry : mapStorage.entrySet()) {
            if (entry.getKey().equals(uid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Person r, String searchKey) {
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
    protected List<Person> doGetAllSorted() {
        List<Person> people = new ArrayList<>();
        TreeMap<Object, Person> sorted = new TreeMap<>(mapStorage);
        people.addAll(sorted.values());
        return people;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
