package main;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SaveAndGet saveAndGet = new SaveAndGet();
        ToDoList toDoList = new ToDoList();

        System.out.println("Neue Liste erstellen oder Liste anzeigen? (Liste löschen) (1/2/3)");
        int input = 0;

        try {
            input = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Falsche eingabe. 1: Liste erstellen / 2: Liste anzeigen");
            main(args);
        }

        switch (input) {
            case 1 -> toDoList.createList();
            case 2 -> toDoList.showList(saveAndGet.getList());
            case 3 -> saveAndGet.deleteList();
        }
    }
}