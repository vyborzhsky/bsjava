package com.rkubyshkin.model;

import java.time.LocalDate;
import java.util.Objects;

public class StructureListUnit {
    private final LocalDate startCareerDate;
    private final LocalDate endCareerDate;
    private final Structure structure;

    @Override
    public String toString() {
        return "StructureListUnit{" +
                "startCareerDate=" + startCareerDate +
                ", endCareerDate=" + endCareerDate +
                ", structure=" + structure +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureListUnit that = (StructureListUnit) o;
        return startCareerDate.equals(that.startCareerDate) &&
                endCareerDate.equals(that.endCareerDate) &&
                Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startCareerDate, endCareerDate, structure);
    }

    public StructureListUnit(LocalDate startCareerDate, LocalDate endCareerDate) {
        Objects.requireNonNull(startCareerDate, "startCareerDate must not be null");
        Objects.requireNonNull(endCareerDate, "endCareerDate must not be null");
        this.startCareerDate = startCareerDate;
        this.endCareerDate = endCareerDate;
        this.structure = null;
    }

    public StructureListUnit(String nameSite, String url, String tittle, String desc, LocalDate startCareerDate, LocalDate endCareerDate) {
        this.structure = new Structure(nameSite, url, tittle, desc);
        Objects.requireNonNull(startCareerDate, "startCareerDate must not be null");
        Objects.requireNonNull(endCareerDate, "endCareerDate must not be null");
        this.startCareerDate = startCareerDate;
        this.endCareerDate = endCareerDate;
    }
}
