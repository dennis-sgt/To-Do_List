package main;

import java.io.*;
import java.util.HashMap;
import java.util.Queue;

public class SaveAndGet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public void saveObject(HashMap<String, Queue<String>> object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("lists.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            System.err.println("Fehler beim Speicher der Liste. " + "(" + e + ")");
        }

        System.out.println("Objekt gespeichert. " + "(" + object.toString() + ")");
    }

    public boolean hasMap() {
        HashMap<String, Queue<String>> map = new HashMap<>();
        boolean result;

        try {
            FileInputStream fileInputStream = new FileInputStream("lists.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            map = (HashMap<String, Queue<String>>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    public void deleteMap() {
        try {
            new FileOutputStream("lists.ser").close();
        } catch (Exception e) {
            System.err.println("Fehler beim Löschen aller Listen " + "(" + e + ")");
        }

        System.out.println("Alle Listen gelöscht.");
    }

    public void deleteList(String name) {
        HashMap<String, Queue<String>> map = getMap();
        if (map.size() > 1) {
            try {
                map.remove(name);
                saveObject(map);
            } catch (Exception e) {
                System.err.println("Fehler beim Löschen der Liste. " + "(" + e + ")");
            }
        } else {
            deleteMap();
        }
        System.out.println("Liste erfolgreich gelöscht");
    }

    public HashMap<String, Queue<String>> getMap() {
        HashMap<String, Queue<String>> map = new HashMap<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("lists.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            map = (HashMap<String, Queue<String>>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();

            return map;
        } catch (Exception e) {
            if (!hasMap()) {
                System.err.println("Keine Listen vorhanden. " + "(" + e + ")");
                Program.main(null);
            } else {
                System.err.println("Fehler beim Anzeigen der Liste. " + "(" + e + ")");
                Program.main(null);
            }
        }
        return map;
    }
}