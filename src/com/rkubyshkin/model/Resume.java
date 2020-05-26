package com.rkubyshkin.model;

import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>{
    private final String uid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uid, String fullName) {
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(uid, "uid must not be null");
        this.uid = uid;
        this.fullName = fullName;
    }

    public String getUid(){
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uid.equals(resume.uid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fullName);
    }

    @Override
    public String toString() {
        return uid + " " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int compareNames = fullName.compareTo(o.fullName);
        return compareNames != 0 ? compareNames : uid.compareTo(o.uid);
    }
}
