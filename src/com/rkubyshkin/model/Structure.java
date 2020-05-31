package com.rkubyshkin.model;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.rkubyshkin.util.DateUtil.NOW;
import static com.rkubyshkin.util.DateUtil.of;


public class Structure{

    private final Link homePage;
    private List<StructureListUnit> position;

    public Structure(String nameSite, String url, StructureListUnit... position) {
        this(new Link(nameSite, url), Arrays.asList(position));
    }
    public Structure(Link homePage, List<StructureListUnit> position) {
        Objects.requireNonNull(position, "tittle must not be null");
        this.homePage = homePage;
        this.position = position;

    }
    @Override
    public String toString() {
        return "Structure{" +
                "homePage=" + homePage +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Structure structure = (Structure) o;
        return homePage.equals(structure.homePage) &&
                position.equals(structure.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, position);
    }

    public static class StructureListUnit {
        private final LocalDate startCareerDate;
        private final LocalDate endCareerDate;
        private final String tittle;
        private final String desc;

        public StructureListUnit(int startCareerYear , Month startCareerMonth, int endCareerYear , Month endCareerMonth, String desc, String tittle) {
            this(of(startCareerYear, startCareerMonth), of(endCareerYear, endCareerMonth), desc, tittle);
        }
        public StructureListUnit(LocalDate startCareerDate, LocalDate endCareerDate, String tittle, String desc) {
            Objects.requireNonNull(startCareerDate, "startCareerDate must not be null");
            Objects.requireNonNull(endCareerDate, "endCareerDate must not be null");
            Objects.requireNonNull(tittle, "title must not be null");
            this.startCareerDate = startCareerDate;
            this.endCareerDate = endCareerDate;
            this.tittle = tittle;
            this.desc = desc;
        }

        public StructureListUnit(int startCareerYear , Month startCareerMonth, String title, String desc) {
            this(of(startCareerYear, startCareerMonth), NOW, title, desc);
        }

        @Override
        public String toString() {
            return "StructureListUnit{" +
                    "startCareerDate=" + startCareerDate +
                    ", endCareerDate=" + endCareerDate +
                    ", tittle='" + tittle + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StructureListUnit that = (StructureListUnit) o;
            return startCareerDate.equals(that.startCareerDate) &&
                    endCareerDate.equals(that.endCareerDate) &&
                    tittle.equals(that.tittle) &&
                    Objects.equals(desc, that.desc);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startCareerDate, endCareerDate, tittle, desc);
        }
    }
}
