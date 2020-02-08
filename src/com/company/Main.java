package com.company;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.Task.TODO.assignedDate;


public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите задачу");

        while (true) {
            System.out.println("Главное меню");
            System.out.println("1. Добавить задание");
            System.out.println("2. Выбрать задание");
            System.out.println("3. Показать список дел");
            System.out.println("4. Завершить работу");

            int menu = sc.nextInt();

            switch (menu) {
                case 1:
                    SimpleDateFormat date_format = new SimpleDateFormat("d/M/yyyy");

                    // Реализована проверка на формат ввода даты: d/M/yyyy
                    for(boolean i = false; i == false; ) {

                        System.out.println("Введите дату:");
                        String date = sc.next();

                        System.out.println("Введите сообщение:");
                        String message = sc.next();
                        System.out.println(message);

                        try {
                            Date time_mark = date_format.parse(date);
                            System.out.println(time_mark.getTime());

                            // Our file.
                            File file = new File("D:\\java.dima13\\ToDo List\\base.txt");

                            // Проверка существует ли файл, и можем ли мы в него записывать.
                            if (file.exists() && file.canWrite()) {
                                System.out.println("Файл существует");

//                                FileReader file_read = new FileReader(file);
//                                int c = file_read.read();
//                                while (c != -1) {
//                                    System.out.print((char)c);
//                                    c = file_read.read();
//                                }
//                                file_read.close();

                                FileWriter file_writer = new FileWriter("base.txt", true);
                                file_writer.write("\n" + time_mark + "\n" + message);
                                file_writer.close();
                            } else {
                                System.out.println("Файла не существет");
                            }



                            i = true;
                        } catch (ParseException e) {
                            System.out.println("Вы ввели дату в неправильном формате.Пожалуйста введите дату в формате {d/M/yyyy}");
                        }
                    }

                    return;
            }
        }
    }
}




