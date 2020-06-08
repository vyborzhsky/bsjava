package com.rkubyshkin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListUnit extends Unit{
    private static final long serialVersionUID = 1L;

    private List<String> items;

    public ListUnit() {
    }
    public ListUnit(String... list) {
        this(Arrays.asList(list));
    }

    public ListUnit(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");

        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListUnit listUnit = (ListUnit) o;
        return items.equals(listUnit.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
