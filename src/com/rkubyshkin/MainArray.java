package com.rkubyshkin;

import com.rkubyshkin.model.Resume;
import com.rkubyshkin.storage.ArrayStorage;
import com.rkubyshkin.storage.Storage;

public class MainArray {
    final static Storage STORAGE = new ArrayStorage();
    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUid("resume1");
        Resume r2 = new Resume();
        r2.setUid("resume2");
        Resume r3 = new Resume();
        r3.setUid("resume3");

        STORAGE.save(r1);
        STORAGE.save(r2);
        STORAGE.save(r3);
        System.out.println(STORAGE.getAll());
    }
}
