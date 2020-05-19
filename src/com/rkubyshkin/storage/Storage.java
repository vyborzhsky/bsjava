package com.rkubyshkin.storage;

import com.rkubyshkin.model.Resume;

public interface Storage {
    void clear();

    void save(Resume r);

    Resume get(String uid);

    void delete(String uid);

    void update(Resume r);

    Resume[] getAll();

    int size();

}
