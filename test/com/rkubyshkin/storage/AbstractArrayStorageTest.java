package com.rkubyshkin.storage;

import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest  {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Person("name"+i, null, null));
        }
        assertThrows(StorageException.class, () -> {
            storage.save(new Person("Overflow", null, null));
        });

    }
}
