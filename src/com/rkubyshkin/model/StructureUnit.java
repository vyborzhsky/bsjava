package com.rkubyshkin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StructureUnit extends Unit{
    private static final long serialVersionUID = 1L;

    private List<Structure> structure;

    public StructureUnit() {
    }

    public StructureUnit(List<Structure> structure) {
        this.structure = structure;
    }

    public StructureUnit(Structure... structure) {
        this(Arrays.asList(structure));
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
