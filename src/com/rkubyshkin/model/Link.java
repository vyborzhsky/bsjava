package com.rkubyshkin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
    private static final long serialVersionUID = 1L;
    private String url;
    private String nameSite;

    public Link() {
    }

    public Link(String nameSite, String url) {
        Objects.requireNonNull(nameSite, "nameSite must not be null");
        this.nameSite = nameSite;
        this.url = url;
    }

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


}
