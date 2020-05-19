package com.rkubyshkin.model;

public class Resume {
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUid(){
        return uid;
    }

    @Override
    public String toString() {
        return uid;
    }
}
