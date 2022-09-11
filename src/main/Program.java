package main;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SaveAndGet saveAndGet = new SaveAndGet();
        ToDoList toDoList = new ToDoList();
        Scanner sc = new Scanner(System.in);

        System.out.println("1: Alle Listen anzeigen | 2: Liste anzeigen | 3: Element zu Liste hinzufügen | 4: Element von Liste entfernen | 5: Liste erstellen | 8: Liste löschen | 9: ALle Listen löschen | 0: Beenden");
        int input = 0;
        try {
            input = sc.nextInt();
        } catch (Exception e) {
            System.err.println("Fehler bei der Auswahl. " + "(" + e + ")");
            main(args);
        }

        switch (input) {
            case 1 -> {
                toDoList.getAllLists();
                main(args);
            }
            case 2 -> {
                if (saveAndGet.hasMap()) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Name der gesuchten Liste:");
                    toDoList.getAllLists();
                    String name = scanner.nextLine();
                    toDoList.getList(name);
                } else {
                    System.err.println("Keine Listen vorhanden.");
                    main(args);
                }
            }
            case 3 -> {
                Scanner scanner = new Scanner(System.in);
                if (saveAndGet.hasMap()) {
                    System.out.println("Name der Liste:");
                    toDoList.getAllLists();
                    String name = scanner.nextLine();
                    toDoList.addToList(name);
                } else {
                    toDoList.getAllLists();
                    main(args);
                }
            }
            case 4 -> {
                Scanner scanner = new Scanner(System.in);
                if (saveAndGet.hasMap() && !saveAndGet.getMap().isEmpty()) {
                    System.out.println("Name der Liste:");
                    toDoList.getAllLists();
                    String name = scanner.nextLine();
                    toDoList.removeElement(name);
                } else {
                    toDoList.getAllLists();
                    main(args);
                }
            }
            case 5 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Name der neuen Liste:");
                String name = scanner.nextLine();
                toDoList.createList(name);
            }
            case 8 -> {
                Scanner scanner = new Scanner(System.in);

                if (saveAndGet.hasMap()) {
                    System.out.println("Name der Liste:");
                    toDoList.getAllLists();
                    String name = scanner.nextLine();
                    saveAndGet.deleteList(name);
                    main(args);
                } else {
                    toDoList.getAllLists();
                    main(args);
                }
            }
            case 9 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1: Bestätigen | 2: Abbrechen");
                int in;
                try {
                    in = scanner.nextInt();

                    switch (in) {
                        case 1 -> {
                            saveAndGet.deleteMap();
                            main(args);
                        }
                        case 2 -> {
                            System.out.println("Löschen abgebrochen");
                            main(args);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Falsche Eingabe. " + "(" + e + ")");
                    main(args);
                }
            }
            case 0 -> System.exit(0);
        }
    }
}