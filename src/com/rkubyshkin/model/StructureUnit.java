package com.rkubyshkin.model;

import java.util.List;
import java.util.Objects;

public class StructureUnit extends Unit{
    private final List<Structure> structure;

    public StructureUnit(List<Structure> structure) {
        Objects.requireNonNull(structure, "structure must not be null");
        this.structure = structure;
    }

    public List<Structure> getStructure() {
        return structure;
    }

    @Override
    public String toString() {
        return structure.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureUnit that = (StructureUnit) o;
        return structure.equals(that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structure);
    }
}
