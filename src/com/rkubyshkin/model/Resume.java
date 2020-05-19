package com.rkubyshkin.model;

import java.util.Objects;

public class Resume {
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUid(){
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uid.equals(resume.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }

    @Override
    public String toString() {
        return uid;
    }
}
