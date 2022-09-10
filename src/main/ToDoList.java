package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ToDoList {
    public void createList() {
        Queue<String> list = new LinkedList<>();
        SaveAndGet saveAndGet = new SaveAndGet();

        try {
            saveAndGet.saveList(list);
        } catch (Exception e) {
            System.out.println("Fehler beim Erstellen der Liste " + e);
        }

        addToList(saveAndGet.getList());
    }

    public void showList(Queue<String> list) {
        if (list == null) {
            System.out.println("Liste ist leer");
        } else {
            for (Object s : list) {
                System.out.println(s);
            }
        }
    }

    private void addToList(Queue<String> list) {
        Scanner sc = new Scanner(System.in);
        SaveAndGet saveAndGet = new SaveAndGet();

        System.out.println("Was soll zur Liste hinzugefügt werden:");
        String element = sc.nextLine();
        list.add(element);
        saveAndGet.saveList(list);
        System.out.println("Noch etwas zur Liste hinzufügen oder anzeigen (1/2)");

        int input = 0;
        try {
            input = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Falsche eingabe. 1: Etwas zur Liste hinzufügen / 2: Liste anzeigen");
            addToList(saveAndGet.getList());
        }

        switch (input) {
            case 1 -> addToList(list);
            case 2 -> showList(saveAndGet.getList());
        }
    }
}