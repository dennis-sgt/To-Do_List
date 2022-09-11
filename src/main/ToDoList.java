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
                System.out.println("1: Add element to the list. | 2: Menu");
                input = scanner.nextInt();
                switch (input) {
                    case 1 -> addToList(name);
                    case 2 -> Program.main(null);
                    default -> {
                        System.err.println("Option not available.");
                        Program.main(null);
                    }
                }
            } catch (Exception e) {
                System.err.println("Incorrect input.");
                Program.main(null);
            }
        }
    }

    public void addToList(String name) {
        Scanner scanner = new Scanner(System.in);
        SaveAndGet saveAndGet = new SaveAndGet();
        HashMap<String, Queue<String>> map = saveAndGet.getMap();

        if (!map.isEmpty() && saveAndGet.hasMap()) {
            System.out.println("What should be added to the list?");
            String element = scanner.nextLine().strip();
            Objects.requireNonNull(map.put(name, map.get(name))).add(element);
            saveAndGet.saveObject(map);

            System.out.println("1: Add something else | 2: Show list | 3: Menu");
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
                        System.err.println("Option not available.");
                        Program.main(null);
                    }
                }
            } catch (Exception e) {
                System.err.println("List with specified name does not exist.");
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
                System.err.println("List with specified name does not exist.");
                Program.main(null);
            }
            System.out.println("Which entry should be removed:");
            String element = scanner.nextLine().strip();
            if (map.get(name).contains(element)) {
                map.get(name).remove(element);
                saveAndGet.saveObject(map);
                System.out.println("Entry successfully removed.");
                Program.main(null);
            } else {
                System.err.println("Entry not in the list");
                Program.main(null);
            }
        } else if (map.isEmpty()) {
            System.err.println("List is empty.");
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

            System.err.println("List is empty.");
            System.out.println("1: Add entry to the list | 2: Add nothing -> Menu");
            int input;

            try {
                input = scanner.nextInt();

                switch (input) {
                    case 1 -> addToList(name);
                    case 2 -> Program.main(null);
                    default -> {
                        System.err.println("Option not available.");
                        Program.main(null);
                    }
                }
            } catch (Exception e) {
                System.err.println("Incorrect input");
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