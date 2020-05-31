package com.rkubyshkin.model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nameSite;

    @Override
    public String toString() {
        return "Link{" +
                "nameSite='" + nameSite + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getNameSite() {
        return nameSite;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return nameSite.equals(link.nameSite) &&
                Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSite, url);
    }

    private final String url;

    public Link(String nameSite, String url) {
        Objects.requireNonNull(nameSite, "nameSite must not be null");
        this.nameSite = nameSite;
        this.url = url;
    }
}
