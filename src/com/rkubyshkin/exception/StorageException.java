package com.rkubyshkin.exception;

public class StorageException extends RuntimeException {
   private final String uid;

    public StorageException(String message, String uid) {
        super(message);
        this.uid = uid;
    }

    public StorageException(String message, String uid, Exception e) {
    super(message, e);
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
