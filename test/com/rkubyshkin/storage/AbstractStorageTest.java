package com.rkubyshkin.storage;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.model.Resume;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

public abstract class AbstractStorageTest {
    protected static Storage storage;
    public static final String UID1="resume1";
    protected static final String FULL_NAME_1 ="IVANOV_AP";
    protected static final Resume RESUME_1 = new Resume(UID1,FULL_NAME_1);
    public static final String UID2="resume2";
    protected static final String FULL_NAME_2 ="PETROV_IS";
    protected static final Resume RESUME_2 = new Resume(UID2,FULL_NAME_2);
    public static final String UID3="resume3";
    protected static final String FULL_NAME_3="SUSLOV_IO";
    protected static final Resume RESUME_3 = new Resume(UID3,FULL_NAME_3);
    public static final String UID4="resume4";
    protected static final String FULL_NAME_4="POPOV_TS";
    protected static final Resume RESUME_4 = new Resume(UID4,FULL_NAME_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
     public void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_1);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void delete() {
        storage.delete(UID1);
        assertSize(2);
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get(UID1);
        });
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete("abc");
        });
    }

    @Test
    public void save() {
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
    public void update() {
        Resume resume = new Resume(UID1,FULL_NAME_1);
        storage.update(resume);
        assertTrue(resume == storage.get(UID1));

    }

    @Test
    public void getAllSorted() {
        List<Resume> array = storage.getAllSorted();
        assertEquals(3, array.size());
        assertEquals(RESUME_1, array.get(0));
        assertEquals(RESUME_2, array.get(1));
        assertEquals(RESUME_3, array.get(2));
    }

    @Test
    public void get() {
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
    public void clear() {
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