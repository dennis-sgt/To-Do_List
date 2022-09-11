package main;

import java.util.*;

public class ToDoList {
    public void createList(String name) {
        SaveAndGet saveAndGet = new SaveAndGet();

        if (!saveAndGet.hasMap()) {
            HashMap<String, Queue<String>> map = new HashMap<>();
            saveAndGet.saveObject(map);
            createList(name);
        } else {
            Scanner scanner = new Scanner(System.in);

            Queue<String> queue = new LinkedList<>();
            HashMap<String, Queue<String>> map = saveAndGet.getMap();
            map.put(name, queue);
            saveAndGet.saveObject(map);
            int input;

            try {
                System.out.println("1: Element zur Liste hinzufügen | 2: Menü");
                input = scanner.nextInt();
                switch (input) {
                    case 1 -> addToList(name);
                    case 2 -> Program.main(null);
                    default -> {
                        System.err.println("Option nicht verfügbar.");
                        Program.main(null);
                    }
                }
            } catch (Exception e) {
                System.err.println("Fehler bei der Auswahl. " + "(" + e + ")");
                Program.main(null);
            }
        }
    }

    public void addToList(String name) {
        Scanner scanner = new Scanner(System.in);
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if (!map.isEmpty() && saveAndGet.hasMap()) {
            System.out.println("Was soll zur Liste hinzugefügt werden?");
            String element = scanner.nextLine().strip();
            Objects.requireNonNull(map.put(name, map.get(name))).add(element);
            saveAndGet.saveObject(map);

            System.out.println("1: Noch etwas hinzufügen | 2: Liste anzeigen | 3: Menü");
            int input;

            try {
                input = scanner.nextInt();
                switch (input) {
                    case 1 -> addToList(name);
                    case 2 -> {
                        getList(name);
                        Program.main(null);
                    }
                    case 3 -> Program.main(null);
                    default -> {
                        System.err.println("Option nicht verfügbar.");
                        Program.main(null);
                    }
                }
            } catch (Exception e) {
                System.err.println("Falsche Eingabe. " + "(" + e + ")");
            }
        } else if (!saveAndGet.hasMap()) {
            createList(name);
        }
    }

    public void removeElement(String name) {
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if (saveAndGet.hasMap() && !map.isEmpty()) {
            Scanner scanner = new Scanner(System.in);

            try {
                getList(name);
            } catch (Exception e) {
                System.err.println("Liste mit eingegebenem Namen nicht vorhanden.");
                Program.main(null);
            }
            System.out.println("Welcher Eintrag soll entfernt werden: ");
            String element = scanner.nextLine().strip();
            if (map.get(name).contains(element)) {
                map.get(name).remove(element);
                saveAndGet.saveObject(map);
                System.out.println("Element erfolgreich entfernt.");
                Program.main(null);
            } else {
                System.err.println("Element nicht in der Liste");
                Program.main(null);
            }
        } else if (map.isEmpty()) {
            System.err.println("Liste ist leer.");
            Program.main(null);
        }
    }

    public void getList(String name) {
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if (saveAndGet.hasMap() && !map.get(name).isEmpty()) {
            int count = 1;
            for (String string : map.get(name)) {
                System.out.println(count + ": " + string);
                count++;
            }
        } else if (map.get(name).isEmpty()) {
            Scanner scanner = new Scanner(System.in);

            System.err.println("Liste ist leer.");
            System.out.println("1: Element zur Liste hinzufügen | 2: Nichts hinzufügen -> Menü");
            int input;

            try {
                input = scanner.nextInt();

                switch (input) {
                    case 1 -> addToList(name);
                    case 2 -> Program.main(null);
                    default -> {
                        System.err.println("Option nicht verfügbar.");
                        Program.main(null);
                    }
                }
            } catch (Exception e) {
                System.err.println("Fehler bei der Auswahl " + "(" + e + ")");
                Program.main(null);
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
        }
    }
}