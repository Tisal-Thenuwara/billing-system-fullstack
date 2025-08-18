package com.pahanaedu.util;

import java.io.*;
import java.util.List;

public class SerializationUtil {

    public static <T extends Serializable> void saveList(List<T> list, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> loadList(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        }
    }
}
