package com.rkubyshkin.model;

import java.util.List;
import java.util.Objects;

public class ListUnit extends Unit{
    private final List<String> items;

    public ListUnit(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");

        this.items = items;
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
