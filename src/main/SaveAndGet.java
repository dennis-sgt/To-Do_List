package main;

import java.io.*;
import java.util.Queue;

public class SaveAndGet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public void saveList(Queue<String> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("list.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern der Liste " + "(" + e + ")");
        }
    }

    public Queue<String> getList() {
        Queue<String> list = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("list.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            list = (Queue<String>) objectInputStream.readObject();
            return list;
        } catch (Exception e) {
            if (list != null) System.out.println("Fehler beim Laden der Liste " + "(" + e + ")");
        }
        return null;
    }

    public void deleteList() {
        try {
            new FileOutputStream("list.ser").close();
        } catch (Exception e) {
            System.out.println("Fehler beim Löschen der Liste " + "(" + e + ")");
        }

        System.out.println("Liste gelöscht");
    }
}