package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ToDoList {
    public void createList(String name) {
        SaveAndGet saveAndGet = new SaveAndGet();

        if (!saveAndGet.hasMap()) {
            HashMap<String, Queue<String>> map = new HashMap<>();
            saveAndGet.saveObject(map);
            createList(name);
        }
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();
        map.put(name, queue);
        saveAndGet.saveObject(map);
        addToList(name);
    }

    public void addToList(String name) {
        Scanner scanner = new Scanner(System.in);
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if(!map.isEmpty() && saveAndGet.hasMap()) {
            System.out.println("Was soll zur Liste hinzugefügt werden?");
            String element = scanner.nextLine();
            map.put(name, map.get(name)).add(element);
            saveAndGet.saveObject(map);

            System.out.println("1: Noch etwas hinzufügen | 2: Liste anzeigen");
            int input = 0;

            try {
                input = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("Falsche Eingabe. " + "(" + e + ")");
            }

            switch (input) {
                case 1 -> addToList(name);
                case 2 -> getList(name);
            }
        } else {
            createList(name);
        }
    }

    public void getList(String name) {
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if(saveAndGet.hasMap() && !map.isEmpty()) {
            int count = 1;
            for (String string : map.get(name)) {
                System.out.println(count + ": " + string);
                count++;
            }
        }
    }

    public void getAllLists() {
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if (saveAndGet.hasMap()) {
            int count = 1;
            for (String string : map.keySet()) {
                System.out.println(count + ": " + string);
                count++;
            }

            if (map.isEmpty()) {
                System.out.println("Keine Listen vorhanden");
            }
        }
    }
}