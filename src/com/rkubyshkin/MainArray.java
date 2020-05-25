package com.rkubyshkin;

import com.rkubyshkin.model.Resume;
import com.rkubyshkin.storage.ArrayStorage;
import com.rkubyshkin.storage.SortedArrayStorage;
import com.rkubyshkin.storage.Storage;

import java.util.ArrayList;

public class MainArray {
    final static Storage STORAGE = new SortedArrayStorage();
    public static void main(String[] args) {
        MainArray test = new MainArray();
        Resume r1 = new Resume("resume1");
        Resume r2 = new Resume("resume2");
        Resume r3 = new Resume("resume3");
        Resume r4 = new Resume("resum5");
        ArrayList list = new ArrayList();



        STORAGE.save(r1);
        STORAGE.save(r2);
        STORAGE.save(r3);
        STORAGE.save(r4);

        STORAGE.update(r4);
        STORAGE.delete("resume1");
        STORAGE.delete("resume33");
        //test.printAll(STORAGE);


    }

    /*public void printAll(Storage storage) {
        for (int i = 0; i < storage.size(); i++) {
            System.out.println(storage.getAllSorted()[i].getUid());
        }
    }*/
}
