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
            System.err.println("Error saving the list.");
        }
    }

    public boolean hasMap() {
        boolean result;

        try {
            FileInputStream fileInputStream = new FileInputStream("lists.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            @SuppressWarnings("unchecked")
            HashMap<String, Queue<String>> map = (HashMap<String, Queue<String>>) objectInputStream.readObject();
            map.clone();
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
            System.err.println("Error when deleting all lists");
        }

        System.out.println("ALL LISTS DELETED.");
    }

    public void deleteList(String name) {
        HashMap<String, Queue<String>> map = getMap();
        if (map.size() > 1) {
            try {
                map.remove(name);
                saveObject(map);
            } catch (Exception e) {
                System.err.println("Error deleting the list.");
            }
        } else {
            deleteMap();
        }
        System.out.println("List successfully deleted");
    }

    public HashMap<String, Queue<String>> getMap() {
        try {
            FileInputStream fileInputStream = new FileInputStream("lists.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            @SuppressWarnings("unchecked")
            HashMap<String, Queue<String>> map = (HashMap<String, Queue<String>>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();

            return map;
        } catch (Exception e) {
            if (!hasMap()) {
                System.err.println("No lists available.");
                Program.main(null);
            } else {
                System.err.println("Error retrieving the list.");
                Program.main(null);
            }
        }
        return null;
    }
}