package com.rkubyshkin.storage;


import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.model.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractStorage<SearchKey> implements Storage {
    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());
    protected static final Comparator<Person> RESUME_COMPARATOR = Comparator.comparing(Person::getUid);

    public void delete(String uid) {
        LOGGER.info(uid + " METHOD DELETE");
        SearchKey searchKey = getExistedSearchKey(uid);
        doDelete(searchKey);
    }

    public void save(Person r) {
        LOGGER.info(r + " METHOD SAVE");
        SearchKey searchKey = getNotExistedSearchKey(r.getUid());
        doSave(r, searchKey);
    }

    public void update (Person r) {
        LOGGER.info(r + " METHOD UPDATE");
        SearchKey searchKey = getExistedSearchKey(r.getUid());
        doUpdate(r, searchKey);
    }

    @Override
    public List<Person> getAllSorted() {
        LOGGER.info( "GETALLSORTED");
        List<Person> people = doGetAllSorted();
        Collections.sort(people, RESUME_COMPARATOR);
        return people;
    }

    public Person get (String uid) {
        LOGGER.info(uid + " METHOD GET");
        SearchKey searchKey = getExistedSearchKey(uid);
        return doGet(searchKey);
    }

    private  SearchKey getExistedSearchKey(String uid) {
        LOGGER.warning("resume with uid:" + uid +" not exist");
        SearchKey searchKey = getSearchKey(uid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uid);
        }
        return searchKey;
    }

    private  SearchKey getNotExistedSearchKey(String uid) {
        LOGGER.warning("resume with uid:" + uid +" already exist");
        SearchKey searchKey = getSearchKey(uid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uid);
        }
        return searchKey;
    }
    protected abstract List<Person> doGetAllSorted();

    protected abstract Person doGet(SearchKey searchKey);

    protected abstract void doSave(Person r, SearchKey searchKey);

    protected abstract SearchKey getSearchKey(String uid);

    protected abstract void doUpdate(Person r, SearchKey searchKey);

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract void doDelete(SearchKey searchKey);
}