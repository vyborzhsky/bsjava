package com.rkubyshkin.storage;
import com.rkubyshkin.model.Resume;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }
    //if resume not present
    public void save(Resume r) {
        if(getIndex(r.getUid()) != -1) {
            System.out.println("RESUME" + r.getUid() + "ALREADY EXIST");
        } else if(size == storage.length) {
            System.out.println("STORAGE OVERFLOW");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uid) {
        int index = getIndex(uid);
        if(index == -1) {
            System.out.println("RESUME" + uid + "NOT EXIST");
            return null;
        }
        return storage[index];
    }
    //if resume presnt
    public void delete(String uid) {
        int index = getIndex(uid);
        if(index == -1) {
            System.out.println("RESUME" + uid + "NOT EXIST");
        } else {
                storage[index] = storage[size - 1];
                storage[size - 1] = null;
                size--;
        }
    }

    //if resume presnt
    public void update(Resume r) {
        int index = getIndex(r.getUid());
        if (index == -1) {
            System.out.println("RESUME" + r.getUid() + "NOT EXIST");
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
    Resume[] result = new Resume[size];
        for (int j = 0; j < size; j++) {
            result[j] = storage[j];
        }
        return result;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uid) {
        for (int j = 0; j < size; j++) {
            if (storage[j].getUid() == uid) {
                return j;
            }
        }
        return -1;
    }
}
