package com.rkubyshkin.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uid) {
        super("Resume " + uid + "already exist",uid);
    }
}
