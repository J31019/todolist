<<<<<<< HEAD
package com.company;

import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import static com.company.Methods.toDoList;

public class Main {

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY");
        int data1 = 0;
        int exitWhile =1;

        // Десериализация
        try {
            FileInputStream fis = new FileInputStream("toDoList.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<Object, HashMap<String, Status>> toDoListSave = (HashMap<Object, HashMap<String, Status>>)ois.readObject();
            ois.close();
            fis.close();
            //System.out.println(toDoListSave);
            toDoList = toDoListSave;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Главное меню
        Scanner s1 = new Scanner(System.in);
        while (exitWhile ==1) {
            System.out.println("Выберете пунк из меню. ");
            System.out.println("1.Добавить задание. ");
            System.out.println("2.Выбрать задание. ");
            System.out.println("3.Показать список дел.  ");
            System.out.println("4.Завершить работу. ");
            int choise = s1.nextInt();
            switch (choise) {
                case 1:
                    Methods methods = new Methods();
                    methods.addTimeTask();
                    break;

                case 2:
                Methods methods2 = new Methods();
                methods2.chooseTask();
                break;
                case 3:

                    Methods methods1 = new Methods();
                    methods1.showToDoList();
                    break;
                case 4:

                    // Сериализация
                    try{
                        FileOutputStream fos = new FileOutputStream("toDoList.bin");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(toDoList);
                        oos.close();
                        fos.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Завершение работы.");
                    exitWhile = 2;
            }
        }
    }
}
=======
package com.company;

import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import static com.company.Methods.toDoList;

public class Main {

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY");
        int data1 = 0;
        int exitWhile =1;

        // Десериализация
        try {
            FileInputStream fis = new FileInputStream("toDoList.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<Object, HashMap<String, Status>> toDoListSave = (HashMap<Object, HashMap<String, Status>>)ois.readObject();
            ois.close();
            fis.close();
            //System.out.println(toDoListSave);
            toDoList = toDoListSave;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Главное меню
        Scanner s1 = new Scanner(System.in);
        while (exitWhile ==1) {
            System.out.println("Выберете пунк из меню. ");
            System.out.println("1.Добавить задание. ");
            System.out.println("2.Выбрать задание. ");
            System.out.println("3.Показать список дел.  ");
            System.out.println("4.Завершить работу. ");
            int choise = s1.nextInt();
            switch (choise) {
                case 1:
                    Methods methods = new Methods();
                    methods.addTimeTask();
                    break;

                case 2:
                Methods methods2 = new Methods();
                methods2.chooseTask();
                break;
                case 3:

                    Methods methods1 = new Methods();
                    methods1.showToDoList();
                    break;
                case 4:

                    // Сериализация
                    try{
                        FileOutputStream fos = new FileOutputStream("toDoList.bin");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(toDoList);
                        oos.close();
                        fos.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Завершение работы.");
                    exitWhile = 2;
            }
        }
    }
}
>>>>>>> 1900b1d3fd7904182b106713e6023eaa98f2f4da
