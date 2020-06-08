package com.rkubyshkin.model;


import com.rkubyshkin.util.AdapterLocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.rkubyshkin.util.DateUtil.NOW;
import static com.rkubyshkin.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Structure implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<StructureListUnit> position;

    public Structure() {
    }

    public Structure(String nameSite, String url, StructureListUnit... position) {
        this(new Link(nameSite, url), Arrays.asList(position));
    }
    public Structure(Link homePage, List<StructureListUnit> position) {
        Objects.requireNonNull(position, "tittle must not be null");
        this.homePage = homePage;
        this.position = position;

    }

    public Link getHomePage() {
        return homePage;
    }

    public List<StructureListUnit> getPosition() {
        return position;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class StructureListUnit implements Serializable{
        private static final long serialVersionUID = 1L;
        @XmlJavaTypeAdapter(AdapterLocalDate.class)
        private  LocalDate startCareerDate;
        @XmlJavaTypeAdapter(AdapterLocalDate.class)
        private  LocalDate endCareerDate;
        private  String tittle;
        private  String desc;

        public LocalDate getStartCareerDate() {
            return startCareerDate;
        }

        public LocalDate getEndCareerDate() {
            return endCareerDate;
        }

        public String getTittle() {
            return tittle;
        }

        public String getDesc() {
            return desc;
        }

        public StructureListUnit() {
        }

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
