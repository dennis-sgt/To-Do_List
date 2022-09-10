package main;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SaveAndGet saveAndGet = new SaveAndGet();
        ToDoList toDoList = new ToDoList();
        Scanner sc = new Scanner(System.in);

        System.out.println("1: Alle Listen anzeigen | 2: Liste anzeigen | 3: Element zu Liste hinzufügen | 4: Liste erstellen | 8: Liste löschen | 9: ALle Listen löschen ");
        int input = 0;
        try {
            input = sc.nextInt();
        } catch (Exception e) {
            System.err.println("Fehler bei der Auswahl. " + "(" + e + ")");
            main(args);
        }

        switch (input) {
            case 1 -> toDoList.getAllLists();
            case 2 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Name der gesuchten Liste: ");
                String name = scanner.nextLine();
                toDoList.getList(name);
            }
            case 3 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Name der Liste: ");
                String name = scanner.nextLine();
                toDoList.addToList(name);
            }
            case 4 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Name der Liste: ");
                String name = scanner.nextLine();
                toDoList.createList(name);
            }
            case 8 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Name der Liste: ");
                String name = scanner.nextLine();
                saveAndGet.deleteList(saveAndGet.getMap(), name);
            }
            case 9 -> saveAndGet.deleteMap();
        }
    }
}