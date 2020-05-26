package com.rkubyshkin.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uid) {
        super("Resume " + uid + " not exist",uid);
    }
}
