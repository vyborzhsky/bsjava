package com.rkubyshkin.model;

import java.util.List;
import java.util.Objects;

public class StructureUnit extends Unit{
    private final List<StructureListUnit> structureListUnits;

    public StructureUnit(List<StructureListUnit> structureListUnits) {
        this.structureListUnits = structureListUnits;
    }

    public List<StructureListUnit> getStructureListUnits() {
        return structureListUnits;
    }

    @Override
    public String toString() {
        return structureListUnits.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureUnit that = (StructureUnit) o;
        return structureListUnits.equals(that.structureListUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureListUnits);
    }
}
