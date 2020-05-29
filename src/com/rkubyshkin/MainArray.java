package com.rkubyshkin;

import com.rkubyshkin.model.Contacts;
import com.rkubyshkin.model.UnitType;
import com.rkubyshkin.model.Person;
import com.rkubyshkin.storage.ArrayStorage;
import com.rkubyshkin.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainArray {

    final static Storage STORAGE = new ArrayStorage();
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person r;
        while (true) {
            System.out.print("Enter one of this command - (list | save fullName | delete uid | get uid | update uid fullName | clear");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Wrong command");
                continue;
        }
            String param = null;
            if (params.length > 1 ) {
                param = params[1];
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(STORAGE.size());
                    break;
                case "save":
                    r = new Person(param);
                    STORAGE.save(r);
                    break;
               /* case "update":
                    r = new Person(param, params[2], about, contacts);
                    STORAGE.update(r);
                    printAll();
                    break;*/
                case "delete":
                    STORAGE.delete(param);
                    printAll();
                    break;
                case "get":
                    System.out.println(STORAGE.get(param));
                    break;
                case "clear":
                    STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    private static void printAll() {
        System.out.println("---");
        System.out.println(STORAGE.getAllSorted().toString());
        System.out.println("---");
    }
}
