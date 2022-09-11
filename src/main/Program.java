package main;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SaveAndGet saveAndGet = new SaveAndGet();
        ToDoList toDoList = new ToDoList();
        Scanner sc = new Scanner(System.in);

        System.out.println("1: Show all lists | 2: Show list | 3: Add entry to list | 4: Remove entry from list | 5: Create list | 8: Delete list | 9: Delete ALL lists | 0: Exit");
        int input;
        try {
            input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    toDoList.getAllLists();
                    main(args);
                }
                case 2 -> {
                    if (saveAndGet.hasMap()) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Name of the list:");
                        toDoList.getAllLists();
                        String name = scanner.nextLine().strip();
                        toDoList.getList(name);
                        main(args);
                    } else {
                        toDoList.getAllLists();
                        main(args);
                    }
                }
                case 3 -> {
                    Scanner scanner = new Scanner(System.in);
                    if (saveAndGet.hasMap()) {
                        System.out.println("Name of the list:");
                        toDoList.getAllLists();
                        String name = scanner.nextLine().strip();
                        toDoList.addToList(name);
                    } else {
                        toDoList.getAllLists();
                        main(args);
                    }
                }
                case 4 -> {
                    Scanner scanner = new Scanner(System.in);
                    if (saveAndGet.hasMap() && !saveAndGet.getMap().isEmpty()) {
                        System.out.println("Name of the list:");
                        toDoList.getAllLists();
                        String name = scanner.nextLine().strip();
                        toDoList.removeElement(name);
                        main(args);
                    } else {
                        toDoList.getAllLists();
                        main(args);
                    }
                }
                case 5 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Name of the new list:");
                    String name = scanner.nextLine().strip();
                    toDoList.createList(name);
                }
                case 8 -> {
                    Scanner scanner = new Scanner(System.in);

                    if (saveAndGet.hasMap()) {
                        System.out.println("Name of the list:");
                        toDoList.getAllLists();
                        String name = scanner.nextLine().strip();
                        saveAndGet.deleteList(name);
                        main(args);
                    } else {
                        toDoList.getAllLists();
                        main(args);
                    }
                }
                case 9 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1: Confirm | 2: Cancel");
                    int in;
                    try {
                        in = scanner.nextInt();

                        switch (in) {
                            case 1 -> {
                                saveAndGet.deleteMap();
                                main(args);
                            }
                            case 2 -> {
                                System.out.println("Deletion canceled");
                                main(args);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Incorrect input.");
                        main(args);
                    }
                }
                case 0 -> System.exit(0);

                default -> {
                    System.err.println("Option not available.");
                    main(args);
                }
            }
        } catch (Exception e) {
            System.err.println("Selection error.");
            main(args);
        }
    }
}