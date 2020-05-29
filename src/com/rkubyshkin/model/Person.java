package com.rkubyshkin.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Person implements Comparable<Person>{
    private final String uid;
    private final String fullName;
    private final Map<Contacts, String> contacts = new EnumMap<>(Contacts.class);
    private final Map<UnitType, Unit> info = new EnumMap<>(UnitType.class);

    public Person(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Person(String uid, String fullName) {

        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(uid, "uid must not be null");
        this.uid = uid;
        this.fullName = fullName;
    }

    public String getUid(){
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return uid.equals(person.uid) &&
                fullName.equals(person.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fullName);
    }

    @Override
    public String toString() {
        return uid + " " + fullName;
    }

    @Override
    public int compareTo(Person o) {
        int compareNames = fullName.compareTo(o.fullName);
        return compareNames != 0 ? compareNames : uid.compareTo(o.uid);
    }

    public String getFullName() {
        return fullName;
    }
}
