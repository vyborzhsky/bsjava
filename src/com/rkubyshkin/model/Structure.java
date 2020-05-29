package com.rkubyshkin.model;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.time.LocalDate;
import java.util.Objects;

public class Structure{
    private final Link homePage;

    @Override
    public String toString() {
        return "Structure{" +
                "homePage=" + homePage +
                ", tittle='" + tittle + '\'' +
                ", desc='" + desc + '\'' +
                ", startCarrerDate=" + startCarrerDate +
                ", endCarrerDate=" + endCarrerDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Structure structure = (Structure) o;
        return Objects.equals(homePage, structure.homePage) &&
                tittle.equals(structure.tittle) &&
                Objects.equals(desc, structure.desc) &&
                startCarrerDate.equals(structure.startCarrerDate) &&
                endCarrerDate.equals(structure.endCarrerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, tittle, desc, startCarrerDate, endCarrerDate);
    }

    public Structure(String nameSite, String url, String tittle, String desc, LocalDate startCarrerDate, LocalDate endCarrerDate) {
        Objects.requireNonNull(startCarrerDate, "startCarrerDate must not be null");
        Objects.requireNonNull(endCarrerDate, "endCarrerDate must not be null");
        Objects.requireNonNull(tittle, "tittle must not be null");

        this.homePage = new Link(nameSite, url);
        this.tittle = tittle;
        this.desc = desc;
        this.startCarrerDate = startCarrerDate;
        this.endCarrerDate = endCarrerDate;
    }

    private final String tittle;
    private final String desc;
    private final LocalDate startCarrerDate;
    private final LocalDate endCarrerDate;

}
