package com.rkubyshkin;

import com.rkubyshkin.model.Resume;
import com.rkubyshkin.storage.ArrayStorage;

public class MainArray {
    static ArrayStorage storage = new ArrayStorage();
    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUid("resume1");
        Resume r2 = new Resume();
        r2.setUid("resume2");
        Resume r3 = new Resume();
        r3.setUid("resume3");

        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        System.out.println(storage.getAll());
    }
}
