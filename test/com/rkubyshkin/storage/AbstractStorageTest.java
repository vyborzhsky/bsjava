package com.rkubyshkin.storage;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.model.Person;
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
    protected static final Person PERSON_1 = new Person(UID1,FULL_NAME_1, information, contacts);
    public static final String UID2="resume2";
    protected static final String FULL_NAME_2 ="PETROV_IS";
    protected static final Person PERSON_2 = new Person(UID2,FULL_NAME_2, information, contacts);
    public static final String UID3="resume3";
    protected static final String FULL_NAME_3="SUSLOV_IO";
    protected static final Person PERSON_3 = new Person(UID3,FULL_NAME_3, information, contacts);
    public static final String UID4="resume4";
    protected static final String FULL_NAME_4="POPOV_TS";
    protected static final Person PERSON_4 = new Person(UID4,FULL_NAME_4, information, contacts);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
     public void setUp() {
        storage.clear();
        storage.save(PERSON_2);
        storage.save(PERSON_1);
        storage.save(PERSON_3);
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
        storage.save(PERSON_4);
        assertSize(4);
        assertGet(PERSON_4);;
    }

    @Test
    public void saveExist() {
        Exception exception = assertThrows(ExistStorageException.class, () -> {
            storage.save(PERSON_1);
        });
    }

    @Test
    public void update() {
        Person person = new Person(UID1,FULL_NAME_1, information, contacts);
        storage.update(person);
        assertTrue(person == storage.get(UID1));

    }

    @Test
    public void getAllSorted() {
        List<Person> array = storage.getAllSorted();
        assertEquals(3, array.size());
        assertEquals(PERSON_1, array.get(0));
        assertEquals(PERSON_2, array.get(1));
        assertEquals(PERSON_3, array.get(2));
    }

    @Test
    public void get() {
        assertGet(PERSON_1);
        assertGet(PERSON_2);
        assertGet(PERSON_3);
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

    private void assertGet(Person r) {
        assertEquals(r, storage.get(r.getUid()));
    }
}