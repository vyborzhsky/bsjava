package com.rkubyshkin.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = 1L;

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

    public void put(ContactsType type, String val) {
        contacts.put(type, val);
    }

    public void addUnit(UnitType type, Unit val) {
        info.put(type, val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return uid.equals(person.uid) &&
                fullName.equals(person.fullName) &&
                Objects.equals(contacts, person.contacts) &&
                Objects.equals(info, person.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fullName, contacts, info);
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
