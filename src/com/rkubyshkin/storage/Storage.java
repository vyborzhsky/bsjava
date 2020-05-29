package com.rkubyshkin.storage;

import com.rkubyshkin.model.Person;
import java.util.List;

public interface Storage {
    void clear();

    void save(Person r);

    Person get(String uid);

    void delete(String uid);

    void update(Person r);

    List<Person> getAllSorted();

    int size();

}
