package com.rkubyshkin.storage;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.model.Resume;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

abstract class AbstractStorageTest {
    protected static Storage storage;
    public static final String UID1="resume1";
    //public static final String FULL_NAME_1 ="IvanovAP";
    protected static final Resume RESUME_1 = new Resume(UID1);
    public static final String UID2="resume2";
    //public static final String FULL_NAME_2 ="PetrovIS";
    protected static final Resume RESUME_2 = new Resume(UID2);
    public static final String UID3="resume3";
    //public static final String FULL_NAME_3="SuslovIO";
    protected static final Resume RESUME_3 = new Resume(UID3);
    public static final String UID4="resume4";
    //public static final String FULL_NAME_4="PopovTS";
    protected static final Resume RESUME_4 = new Resume(UID4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setup() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_1);
        storage.save(RESUME_3);
    }
    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void delete() throws Exception {
        storage.delete(UID1);
        assertSize(2);
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get(UID1);
        });
    }

    @Test
    public void deleteNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.delete("abc");
        });
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);;
    }

    @Test
    public void saveExist() {
        Exception exception = assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_1);
        });
    }

    @Test
    void update() {
        Resume resume = new Resume(UID1);
        storage.update(resume);
        assertTrue(resume == storage.get(UID1));

    }

    @Test
    void getAllSorted() {
        List<Resume> array = storage.getAllSorted();
        assertEquals(3, array.size());
        assertEquals(RESUME_1, array.get(0));
        assertEquals(RESUME_2, array.get(1));
        assertEquals(RESUME_3, array.get(2));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get("abc");
        });
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }


    public void updateNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get("abc");
        });
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUid()));
    }
}