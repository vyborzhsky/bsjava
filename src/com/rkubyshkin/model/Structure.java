package com.rkubyshkin.model;

import java.util.Objects;

public class Structure{

    private final Link homePage;
    private final String tittle;
    private final String desc;


    public Structure(String nameSite, String url, String tittle, String desc) {
        Objects.requireNonNull(tittle, "tittle must not be null");
        this.homePage = new Link(nameSite, url);
        this.tittle = tittle;
        this.desc = desc;
    }

    public Structure() {
        this.homePage = null;
        this.tittle = null;
        this.desc = null;
    }
}
