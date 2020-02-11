package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите задачу");

        while (true) {
            System.out.println("Главное меню");
            System.out.println("1. Добавить задание");
            System.out.println("2. Выбрать задание");
            System.out.println("3. Показать список дел");
            System.out.println("4. Завершить работу");

            String menu = sc.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

            switch (menu) {
                case "1":

                    // Реализована проверка на формат ввода даты: d/M/yyyy
                    System.out.println("Введите дату:");
                    String date = sc.nextLine();

                    System.out.println("Введите сообщение:");
                    String message = sc.nextLine();

                    try {
                        Date timeMark = dateFormat.parse(date);
                        System.out.println(timeMark);

                        Task task = new Task(message, timeMark);
                        taskList.addTask(task);

                        // Our file.
                        File file = new File("D:\\base.txt");

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
                            file_writer.write("\n" + timeMark + "\n" + message);
                            file_writer.close();
                        } else {
                            System.out.println("Файла не существет");
                        }
                    } catch (ParseException e) {
                        System.out.println("Вы ввели дату в неправильном формате.Пожалуйста введите дату в формате {d/M/yyyy}");
                    }
                    break;


                case "2":
                    System.out.println("1. Изменение описания");
                    System.out.println("2. Удалить");
                    System.out.println("3. Пометить как выполненое");
                    System.out.println("4. Пометить как невыполненое");
                    System.out.println("5. Отмена");



                case "3":
                    System.out.println("Показать список дел");
                    System.out.println("1. На сегодня");
                    System.out.println("2. На эту неделю");
                    System.out.println("3. На выбранную дату");
                    System.out.println("4. Весь список");
                    System.out.println("5. Назад");

                    String menuPrint = sc.nextLine();

                    Date conditionDate = new Date();
                    LocalDate conditionLocalDate = new LocalDate();

                 //   LocalDateTime conditionLocalDateTime = new LocalDateTime(System.currentTimeMillis());

                    switch(menuPrint) {
                        case "1":
                            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                                if (conditionDate.getDate().equals(taskList.getTaskList().get(i).getDate().getDate())) {
                                    System.out.println(i + " " + taskList.getTaskList().get(i).getDescription());
                                }
                            }
                            break;

                        case "2":
                            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                                System.out.println(i + " " + taskList.getTaskList().get(i).getDescription());
                            }
                            break;

                        case "3":
                            System.out.println("Введите дату:");
                            String datePrint = sc.nextLine();
                            try {
                                conditionDate = dateFormat.parse(datePrint);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                                if (conditionDate.equals(taskList.getTaskList().get(i).getDate())) {
                                    System.out.println(i + " " + taskList.getTaskList().get(i).getDescription());
                                }
                            }
                            break;
                    }


//                   for (int i = 0; i < taskList.getTaskList().size(); i++) {
//                        System.out.println(i + " " + taskList.getTaskList().get(i).getDescription());
//                   }

            }
        }
    }
}




