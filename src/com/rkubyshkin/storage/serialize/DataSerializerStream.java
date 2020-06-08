package com.rkubyshkin.storage.serialize;

import com.rkubyshkin.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataSerializerStream implements SerializerStream {

    @Override
    public void doWrite(Person r, OutputStream outStream) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(outStream)) {
            dos.writeUTF(r.getUid());
            dos.writeUTF(r.getFullName());
            Map<UnitType, Unit> info = r.getInfo();
            Map<ContactsType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry-> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeCollection(dos, info.entrySet(), entry -> {
                        UnitType type = entry.getKey();
                        Unit unit = entry.getValue();
                        dos.writeUTF(type.name());
                        switch (type) {
                            case HOBBY:
                            case LOCATION:
                                dos.writeUTF(((TextUnit) unit).getTextContent());
                                break;
                            case WORK:
                            case EDUCATION:
                                writeCollection(dos, ((StructureUnit) unit).getStructure(), struct -> {
                                    dos.writeUTF(struct.getHomePage().getNameSite());
                                    dos.writeUTF(struct.getHomePage().getUrl());
                                    writeCollection(dos, struct.getPosition(), position -> {
                                        writeLocalDate(dos, position.getStartCareerDate());
                                        writeLocalDate(dos, position.getEndCareerDate());
                                        dos.writeUTF(position.getTittle());
                                        dos.writeUTF(position.getDesc());
                                    });
                                    });
                                break;
                            case PETS:
                            case FAMILY:
                                writeCollection(dos,((ListUnit) unit).getItems(), dos::writeUTF);
                                break;
                        }
                    });
        }
    }

    @Override
    public Person doRead(InputStream inStream) throws IOException {
        try (DataInputStream dis = new DataInputStream(inStream)) {
            String uid = dis.readUTF();
            String fullName = dis.readUTF();
            Person person = new Person(uid, fullName);
            readItems(dis,()->
                person.addContact(ContactsType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis,()-> {
                UnitType unitType = UnitType.valueOf(dis.readUTF());
                person.addUnit(unitType, readUnit(dis, unitType));
            });
            return person;
        }
    }

    private Unit readUnit(DataInputStream dis, UnitType unitType) throws IOException {
        switch (unitType) {
            case HOBBY:
            case LOCATION:
                return new TextUnit(dis.readUTF());
            case WORK:
            case EDUCATION:
                return new StructureUnit(
                        readList(dis, ()-> new Structure(
                                new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, ()-> new Structure.StructureListUnit(
                                        readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                                ))
                        )));
            case PETS:
            case FAMILY:
                return new ListUnit(readList(dis, dis::readUTF));
            default:
                throw new IllegalStateException();
        }
    }
    private <T> List<T> readList(DataInputStream dis,  ElementReader<T> elementReader) throws IOException {
     int size = dis.readInt();
     List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(elementReader.read());
        }
        return list;
    }

    private interface  ElementReader<T> {
        T read() throws IOException;
    }

    private interface  ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface  ElementProcess<T> {
        void process() throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcess proc) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            proc.process();
        }
    }
    private <T> void writeCollection (DataOutputStream dos, Collection<T> coll, ElementWriter<T> writer) throws IOException {
        dos.writeInt(coll.size());
        for(T item: coll) {
            writer.write(item);
        }
    }
    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }
    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}
