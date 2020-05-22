package com.rkubyshkin.storage;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Resume;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class AbstractArrayStorageTest {
    private Storage storage;
    public static final String UID1="resume1";
    protected static final Resume RESUME_1 = new Resume(UID1);
    public static final String UID2="resume2";
    protected static final Resume RESUME_2 = new Resume(UID2);
    public static final String UID3="resume3";
    protected static final Resume RESUME_3 = new Resume(UID3);
    public static final String UID4="resume4";
    protected static final Resume RESUME_4 = new Resume(UID4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setup() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
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
    public void saveOverflow() {
        for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        Exception exception = assertThrows(StorageException.class, () -> {
           storage.save(new Resume());
        });

    }

    @Test
    void update() {
        Resume resume = new Resume(UID1);
        storage.update(resume);
        assertTrue(resume == storage.get(UID1));

    }
    public void updateNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get("abc");
        });
    }
    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_3, array[2]);
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



    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUid()));
    }
}