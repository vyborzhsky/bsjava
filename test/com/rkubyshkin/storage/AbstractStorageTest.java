package com.rkubyshkin.storage;

import com.rkubyshkin.Config;
import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStorageTest {
    protected static final File DIR_STORAGE = Config.get().getStorageDir();
    protected static Storage storage;
    public static final String UID1="person1";
    protected static final String FULL_NAME_1 ="IVANOV_AP";
    protected static final Map<ContactsType, String> CONTACTS_PERSON_1 = new EnumMap<>(ContactsType.class);
    protected static final Map<UnitType, Unit> INFO_PERSON_1 = new EnumMap<>(UnitType.class);
    protected static final Person PERSON_1 = new Person(UID1,FULL_NAME_1/*, INFO_PERSON_1, CONTACTS_PERSON_1*/);
    protected static final Unit TEXT_UNIT_LOCATION_1 = new TextUnit("Moscow City Portneu st. d1. kv87");
    protected static final Unit TEXT_UNIT_HOBBY_1 = new TextUnit("Я занимаюсь вязанием и пошивом штанов");
    protected static List <String> LIST_OF_FAMILY_1 = new ArrayList<>();
    protected static List <String> LIST_OF_PETS_1 = new ArrayList<>();
    protected static final Unit TEXT_LIST_UNIT_FAMILY_1 = new ListUnit(LIST_OF_FAMILY_1);
    protected static final Unit TEXT_LIST_UNIT_PETS_1 = new ListUnit(LIST_OF_PETS_1);

    public static final String UID2="person2";
    protected static final String FULL_NAME_2 ="PETROV_IS";
    protected static final Map<ContactsType, String> CONTACTS_PERSON_2 = new EnumMap<>(ContactsType.class);
    protected static final Map<UnitType, Unit> INFO_PERSON_2 = new EnumMap<>(UnitType.class);
    protected static final Person PERSON_2 = new Person(UID2,FULL_NAME_2/*, INFO_PERSON_2, CONTACTS_PERSON_2*/);
    protected static final Unit TEXT_UNIT_LOCATION_2 = new TextUnit("SaintPeter City Abraham st. d2. kv1");
    protected static final Unit TEXT_UNIT_HOBBY_2 = new TextUnit("Я занимаюсь выращиванием огурцов");
    protected static List <String> LIST_OF_FAMILY_2 = new ArrayList<>();
    protected static List <String> LIST_OF_PETS_2 = new ArrayList<>();
    protected static final Unit TEXT_LIST_UNIT_FAMILY_2 = new ListUnit(LIST_OF_FAMILY_2);
    protected static final Unit TEXT_LIST_UNIT_PETS_2 = new ListUnit(LIST_OF_PETS_2);

    public static final String UID3="person3";
    protected static final String FULL_NAME_3="SUSLOV_IO";
    protected static final Map<ContactsType, String> CONTACTS_PERSON_3 = new EnumMap<>(ContactsType.class);
    protected static final Map<UnitType, Unit> INFO_PERSON_3 = new EnumMap<>(UnitType.class);
    protected static final Person PERSON_3 = new Person(UID3,FULL_NAME_3/*, INFO_PERSON_3, CONTACTS_PERSON_3*/);
    protected static final Unit TEXT_UNIT_LOCATION_3 = new TextUnit("Reutov City Goooots st. d7. kv33");
    protected static final Unit TEXT_UNIT_HOBBY_3 = new TextUnit("Я занимаюсь строительством домов");
    protected static List <String> LIST_OF_FAMILY_3 = new ArrayList<>();
    protected static List <String> LIST_OF_PETS_3 = new ArrayList<>();
    protected static final Unit TEXT_LIST_UNIT_FAMILY_3 = new ListUnit(LIST_OF_FAMILY_3);
    protected static final Unit TEXT_LIST_UNIT_PETS_3 = new ListUnit(LIST_OF_PETS_3);

    public static final String UID4="person4";
    protected static final String FULL_NAME_4="POPOV_TS";
    protected static final Map<ContactsType, String> CONTACTS_PERSON_4 = new EnumMap<>(ContactsType.class);
    protected static final Map<UnitType, Unit> INFO_PERSON_4 = new EnumMap<>(UnitType.class);
    protected static final Person PERSON_4 = new Person(UID4,FULL_NAME_4/*, INFO_PERSON_4, CONTACTS_PERSON_4*/);
    protected static final Unit TEXT_UNIT_LOCATION_4 = new TextUnit("Novosibirsk City Treutt st. d1. kv66");
    protected static final Unit TEXT_UNIT_HOBBY_4 = new TextUnit("Я занимаюсь коллекционированием машин");
    protected static List <String> LIST_OF_FAMILY_4 = new ArrayList<>();
    protected static List <String> LIST_OF_PETS_4 = new ArrayList<>();
    protected static final Unit TEXT_LIST_UNIT_FAMILY_4 = new ListUnit(LIST_OF_FAMILY_4);
    protected static final Unit TEXT_LIST_UNIT_PETS_4 = new ListUnit(LIST_OF_PETS_4);
    protected static List <Structure.StructureListUnit> LIST_OF_EDUC_4 = new ArrayList<>();
    protected static List <Structure.StructureListUnit> LIST_OF_WORK_4 = new ArrayList<>();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
     public void setUp() {
        /*LIST_OF_FAMILY_1.add("Отец Григорий");
        LIST_OF_FAMILY_1.add("Мать Наталья");
        LIST_OF_FAMILY_1.add("Сын Олег");
        LIST_OF_PETS_1.add("Хомяк Дмитрий");
        LIST_OF_PETS_1.add("Кот Илья");

        LIST_OF_FAMILY_2.add("Отец Анатолий");
        LIST_OF_FAMILY_2.add("Мать Надежда");
        LIST_OF_FAMILY_2.add("Дочь Юля");
        LIST_OF_PETS_2.add("Пес Бобби");
        LIST_OF_PETS_2.add("Кошка Уша");

        LIST_OF_FAMILY_3.add("Отец Владимир");
        LIST_OF_FAMILY_3.add("Мать Елена");
        LIST_OF_FAMILY_3.add("Сын Андрей");
        LIST_OF_PETS_3.add("Кот Кен");
        LIST_OF_PETS_3.add("Белка Мария");

        LIST_OF_FAMILY_4.add("Отец Виктор");
        LIST_OF_FAMILY_4.add("Мать Саша");
        LIST_OF_FAMILY_4.add("Дочь Наталья");
        LIST_OF_PETS_4.add("Угорь Илюша");
        LIST_OF_PETS_4.add("Питон Болли");

        CONTACTS_PERSON_1.put(ContactsType.EMAIL,"person1@gmail.com");
        CONTACTS_PERSON_2.put(ContactsType.EMAIL,"person2@gmail.com");
        CONTACTS_PERSON_3.put(ContactsType.EMAIL,"person3@gmail.com");
        CONTACTS_PERSON_4.put(ContactsType.EMAIL,"person4@gmail.com");
        CONTACTS_PERSON_1.put(ContactsType.NUMBERPHONE,"+89260000000");
        CONTACTS_PERSON_2.put(ContactsType.NUMBERPHONE,"+89261111111");
        CONTACTS_PERSON_3.put(ContactsType.NUMBERPHONE,"+89262222222");
        CONTACTS_PERSON_4.put(ContactsType.NUMBERPHONE,"+89263333333");
        CONTACTS_PERSON_1.put(ContactsType.LINKEDIN,"linkedin.com/person1");
        CONTACTS_PERSON_2.put(ContactsType.LINKEDIN,"linkedin.com/person2");
        CONTACTS_PERSON_3.put(ContactsType.LINKEDIN,"linkedin.com/person3");
        CONTACTS_PERSON_4.put(ContactsType.LINKEDIN,"linkedin.com/person4");
        CONTACTS_PERSON_1.put(ContactsType.TELEGRAM,"@person1");
        CONTACTS_PERSON_2.put(ContactsType.TELEGRAM,"@person2");
        CONTACTS_PERSON_3.put(ContactsType.TELEGRAM,"@person3");
        CONTACTS_PERSON_4.put(ContactsType.TELEGRAM,"@person4");
        CONTACTS_PERSON_1.put(ContactsType.VK,"@vk.com/person1");
        CONTACTS_PERSON_2.put(ContactsType.VK,"@vk.com/person2");
        CONTACTS_PERSON_3.put(ContactsType.VK,"@vk.com/person3");
        CONTACTS_PERSON_4.put(ContactsType.VK,"@vk.com/person4");

        INFO_PERSON_1.put(UnitType.FAMILY,TEXT_LIST_UNIT_FAMILY_1);
        INFO_PERSON_1.put(UnitType.EDUCATION, new StructureUnit(new Structure("MGTU", "MGTU.RU",
                new Structure.StructureListUnit(
                        2210, Month.DECEMBER,2311,Month.APRIL, "MGTU", "MGTU")),
                new Structure("MGTU2", "MGTU2.RU",
                        new Structure.StructureListUnit(
                                2212, Month.DECEMBER,2315,Month.APRIL, "MGTU2", "MGTU2"))
        ));
        INFO_PERSON_1.put(UnitType.HOBBY,TEXT_UNIT_HOBBY_1);
        INFO_PERSON_1.put(UnitType.LOCATION,TEXT_UNIT_LOCATION_1);
        INFO_PERSON_1.put(UnitType.PETS,TEXT_LIST_UNIT_PETS_1);
        INFO_PERSON_1.put(UnitType.WORK,new StructureUnit(new Structure("MISIS", "MISIS.RU",
                new Structure.StructureListUnit(2211,Month.DECEMBER,"MISIS", "MISIS"))));

        INFO_PERSON_2.put(UnitType.FAMILY,TEXT_LIST_UNIT_FAMILY_2);
        INFO_PERSON_2.put(UnitType.EDUCATION,new StructureUnit(new Structure("MAI", "MAI.RU",
                new Structure.StructureListUnit(2212, Month.DECEMBER,2311,Month.NOVEMBER, "MAI", "MAI"))));
        INFO_PERSON_2.put(UnitType.HOBBY,TEXT_UNIT_HOBBY_2);
        INFO_PERSON_2.put(UnitType.LOCATION,TEXT_UNIT_LOCATION_2);
        INFO_PERSON_2.put(UnitType.PETS,TEXT_LIST_UNIT_PETS_2);
        INFO_PERSON_2.put(UnitType.WORK, new StructureUnit(new Structure("MEI", "MEI.RU",
                new Structure.StructureListUnit(2213, Month.DECEMBER,2310,Month.SEPTEMBER, "MEI", "MEI"))));

        INFO_PERSON_3.put(UnitType.FAMILY,TEXT_LIST_UNIT_FAMILY_3);
        INFO_PERSON_3.put(UnitType.EDUCATION, new StructureUnit(new Structure("PLEHANOV", "PLEHANOV.RU",
                new Structure.StructureListUnit(2214, Month.DECEMBER,2310,Month.AUGUST, "PLEHANOV", "PLEHANOV"))));
        INFO_PERSON_3.put(UnitType.HOBBY,TEXT_UNIT_HOBBY_3);
        INFO_PERSON_3.put(UnitType.LOCATION,TEXT_UNIT_LOCATION_3);
        INFO_PERSON_3.put(UnitType.PETS,TEXT_LIST_UNIT_PETS_3);
        INFO_PERSON_3.put(UnitType.WORK, new StructureUnit(new Structure("MGU", "MGU.RU",
                new Structure.StructureListUnit(2215, Month.DECEMBER,2315,Month.APRIL, "MGU", "MGU"))));

        INFO_PERSON_4.put(UnitType.EDUCATION, new StructureUnit(new Structure("RGGU", "RGGU.RU",
                new Structure.StructureListUnit(2210, Month.DECEMBER,2311,Month.MARCH, "RGGU", "RGGU"))));
        INFO_PERSON_4.put(UnitType.LOCATION,TEXT_UNIT_LOCATION_4);
        INFO_PERSON_4.put(UnitType.PETS,TEXT_LIST_UNIT_PETS_4);
        INFO_PERSON_4.put(UnitType.HOBBY,TEXT_UNIT_HOBBY_4);
        INFO_PERSON_4.put(UnitType.FAMILY,TEXT_LIST_UNIT_FAMILY_4);
        INFO_PERSON_4.put(UnitType.WORK, new StructureUnit(new Structure("MEPHI", "MEPHI.RU",
                new Structure.StructureListUnit(2211, Month.DECEMBER,2316,Month.AUGUST, "MEPHI", "MEPHI"))));

*/



        storage.clear();
        storage.save(PERSON_2);
        storage.save(PERSON_1);
        storage.save(PERSON_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void delete() {
        storage.delete(UID1);
        assertSize(2);
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get(UID1);
        });
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete("abc");
        });
    }

    @Test
    public void save() {
        storage.save(PERSON_4);
        assertSize(4);
        assertGet(PERSON_4);;
    }

    @Test
    public void saveExist() {
        Exception exception = assertThrows(ExistStorageException.class, () -> {
            storage.save(PERSON_1);
        });
    }

    @Test
    public void update() {
        Person person = new Person(UID1,FULL_NAME_1/*, INFO_PERSON_1, CONTACTS_PERSON_1*/);
        storage.update(person);
        assertTrue(person.equals(storage.get(UID1)));

    }

    @Test
    public void getAllSorted() {
        List<Person> array = storage.getAllSorted();
        assertEquals(3, array.size());
        assertEquals(PERSON_1, array.get(0));
        assertEquals(PERSON_2, array.get(1));
        assertEquals(PERSON_3, array.get(2));
    }

    @Test
    public void get() {
        assertGet(PERSON_1);
        assertGet(PERSON_2);
        assertGet(PERSON_3);
    }

    @Test
    public void getNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get("abc");
        });
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }


    public void updateNotExist() {
        Exception exception = assertThrows(NotExistStorageException.class, () -> {
            storage.get("abc");
        });
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Person r) {
        assertEquals(r, storage.get(r.getUid()));
    }
}