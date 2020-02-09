package com.company;

import java.io.*;
import java.util.HashMap;

/**
 * Создаем файл для записи
 * Сохранении в файл
 * открытие файла
 */

public class MyFile {



    public static HashMap openFile() throws IOException {
        HashMap<String, HashMap> hashMap = new HashMap<>();
        createFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("BaseList.txt"));
        while (bufferedReader.ready()) {
            String[] str = bufferedReader.readLine().split("  |  ");

            TaskImplementation temp =
                    TaskImplementation.TRUE.name().equals(str[4]) ? TaskImplementation.TRUE : TaskImplementation.FALSE;

            if (!hashMap.containsKey(str[0])) {
                HashMap<String, TaskImplementation> hashMap1 = new HashMap<>();
                hashMap1.put(str[2], temp);
                hashMap.put(str[0], hashMap1);
            } else {
                HashMap<String, TaskImplementation> hashMap1 = new HashMap<>(hashMap.get(str[0]));
                hashMap1.put(str[2], temp);
                hashMap.put(str[0], hashMap1);
            }
        }
        return hashMap;
    }


    public static void toFile(HashMap<String, HashMap> hashMap) throws IOException {
        String string = "";
        for (String entry : hashMap.keySet()) {
            HashMap<String, TaskImplementation> task = new HashMap<>(hashMap.get(entry));
            for (String entry1 : task.keySet()) {
                string += (entry + "  |  " + entry1 + "  |  " + task.get(entry1) + "\n");
            }
        }
        createBase(string);
    }

    public static void createBase(String str) throws IOException {
        createFile();
        FileWriter fw = new FileWriter("BaseList.txt");
        fw.append(str);
        fw.flush();
        fw.close();
    }


    public static void createFile() throws IOException {
        File baseList = new File("BaseList.txt");
        if (!baseList.exists()) {
            baseList.createNewFile();
        }
    }
}
