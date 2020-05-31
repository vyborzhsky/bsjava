package com.rkubyshkin.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Person implements Comparable<Person>{
    private final String uid;
    private final String fullName;
    private Map<ContactsType, String> contacts;
    private Map<UnitType, Unit> info;

    public Person(String fullName, Map<UnitType, Unit> info, Map<ContactsType, String> contacts) {
        this(UUID.randomUUID().toString(), fullName, info, contacts);
    }

    public Person(String uid, String fullName, Map<UnitType, Unit> info, Map<ContactsType, String> contacts) {

        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(uid, "uid must not be null");
        this.uid = uid;
        this.fullName = fullName;
        this.contacts = contacts;
        this.info = info;
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

    public Unit getInfo(UnitType type) {
        return info.get(type);
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }
}
