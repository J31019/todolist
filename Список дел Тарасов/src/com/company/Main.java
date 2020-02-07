package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        File file = new File("ListOfCases.bin");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream("ListOfCases.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                map = (Map<String, ArrayList<ListOfCases>>) objectInputStream.readObject();           ////   down casting

                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
       }

        byte fExit = 0;
        do {
            System.out.println("Выберите пункт меню\n");

            System.out.println("1. Добавить задание");
            System.out.println("2. Выбрать задание для редактирования");
            System.out.println("3. Показать список дел");
            System.out.println("4. Завершить работу");

            Scanner scanner = new Scanner(System.in);
            int menuOne = scanner.nextInt();

           if (menuOne != 1 && menuOne != 4 && map.isEmpty())
               System.err.println("Список заданий пуст\n");
           else {
                switch (menuOne) {
                    case 1:
                        ListOfCases listOfCases = new ListOfCases();

                        /////////   Ввод даты  //////////
                        System.out.println("Введите дату в формате “DD/MM/YYYY”");
                        String stringDate = scanner.nextLine();
                        stringDate = scanner.nextLine();

                        while (!isDateValid(stringDate)) {
                            System.err.println("Некорректный ввод, попробуйте еще раз!");
                            stringDate = scanner.nextLine();
                        }
                        listOfCases.setDate(stringDate);

                        /////   Ввод задания  //////////
                        System.out.println("Введите задание");
                        String task = scanner.nextLine();
                        listOfCases.setTask(task);

                        /////   Ввод статуса  ////////
                        Status status = Status.UNREALIZED;
                        listOfCases.setStatus(status);

                        //////////  Добавление в HashMap   ////////////
                        add(listOfCases, stringDate);

                        /////////  Сохранение в файл ////////////////
                        saveToFile();
                        break;

                    case 2:
                        fExit = 2;
                        do {
                            System.out.println("Введите дату РЕДАКТИРУЕМОГО ДЕЛА в формате “DD/MM/YYYY”");
                            stringDate = scanner.nextLine();
                            stringDate = scanner.nextLine();

                            while (!isDateValid(stringDate)) {
                                System.err.println("Некорректный ввод, попробуйте еще раз!");
                                stringDate = scanner.nextLine();
                            }

                            if (map.containsKey(stringDate)) {
                                arrayOfCases = map.get(stringDate);
                                for (int i = 0; i < arrayOfCases.size(); i++)
                                    System.out.printf("%d - %s\n", i + 1, arrayOfCases.get(i));
                            } else {
                                System.out.println("На этот день ничего не запланировано\n");
                                fExit = 0;
                                break;
                            }

                            System.out.println("Выберите порядковый номер со списка");

                            int favouritesNumber = scanner.nextInt();

                            while (favouritesNumber <= 0 || favouritesNumber >arrayOfCases.size()) {
                                System.err.println("Некорректный выбор, попробуйте еще раз");
                                favouritesNumber = scanner.nextByte();
                            }

                            //////  редактируемое дело   ////////////
                            ListOfCases favourites = arrayOfCases.get(favouritesNumber - 1);
                            System.out.println(favourites);

                            System.out.println("Выберите пункт меню\n");

                            System.out.println("1. Изменить описание");
                            System.out.println("2. Удалить");
                            System.out.println("3. Пометить как выполненное");
                            System.out.println("4. Пометить как невыполненное");
                            System.out.println("5. Отмена");

                            int menuTwo = scanner.nextInt();

                            switch (menuTwo) {
                                case 1:
                                    System.out.println("Введите новое задание");
                                    String newTask = scanner.nextLine();
                                    newTask = scanner.nextLine();

                                    if (favourites.getTask().equals(newTask))
                                        System.err.println("Такое задание существует");
                                    else {
                                        favourites.setTask(newTask);
                                        saveToFile();
                                        System.out.println("Задание успешно отредактировано!");
                                    }
                                    fExit = 0;
                                    break;

                                case 2:

                                    System.out.println("Вы действительно хотите удалить дело со списка?");

                                    System.out.println("1. Yes");
                                    System.out.println("2. No");

                                    byte andNo = scanner.nextByte();

                                    switch (andNo) {
                                        case 1:
                                            arrayOfCases.remove(favouritesNumber - 1);

                                            if (arrayOfCases.isEmpty()) {
                                                map.remove(stringDate);
                                            }

                                            saveToFile();
                                            System.out.println("Задание успешно удалено!");
                                            fExit = 0;
                                            break;

                                        case 2:
                                            fExit = 0;
                                            break;

                                        default:
                                            System.err.println("Некорректный выбор!");
                                            break;
                                    }
                                    break;

                                case 3:
                                    status = Status.REALIZED;
                                    favourites.setStatus(status);
                                    saveToFile();
                                    fExit = 0;
                                    break;

                                case 4:
                                    status = Status.UNREALIZED;
                                    favourites.setStatus(status);
                                    saveToFile();
                                    fExit = 0;
                                    break;

                                case 5:
                                    fExit = 0;
                                    break;

                                default:
                                    System.err.println("Некорректный выбор");
                                    break;
                            }
                        }while (fExit == 2);
                    break;

                    case 3:
                        fExit = 3;
                        do {
                            System.out.println("Выберите пункт меню");
                            System.out.println("1. На сегодня");
                            System.out.println("2. На эту неделю");
                            System.out.println("3. На выбранную дату");
                            System.out.println("4. Весь список");
                            System.out.println("5. Назад");

                            Calendar calendar = Calendar.getInstance();

                            scanner = new Scanner(System.in);
                            int menuThree = scanner.nextInt();

                            switch (menuThree) {
                                case 1:
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    String name = simpleDateFormat.format(calendar.getTime());
                                    System.out.println(name);

                                    if (map.containsKey(name)) {
                                        arrayOfCases = map.get(name);

                                        for (int i = 0; i < arrayOfCases.size(); i++)
                                            System.out.printf("%d - %s\n", i + 1, arrayOfCases.get(i));
                                    } else
                                        System.out.println("На этот день ничего не запланировано\n");
                                    break;

                                case 2:
                                    calendar.setTime(new Date());
                                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //Устанавливаем понедельник на календаре, будто сейчас понедельник
                                    List<String> day = new ArrayList<>();

                                    for(int i = 0; i < 7; i++){
                                        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                        name = simpleDateFormat.format(calendar.getTime());
                                        day.add(name);
                                        calendar.add(Calendar.DAY_OF_WEEK, 1); //Прибавляем сутки
                                    }

                                    for (int i = 0; i < day.size(); i++) {
                                        System.out.println(day.get(i));
                                        if (map.containsKey(day.get(i))) {
                                            arrayOfCases = map.get(day.get(i));
                                            for (int j = 0; j < arrayOfCases.size(); j++)
                                                System.out.printf("%d - %s\n", j + 1, arrayOfCases.get(j));
                                        } else
                                            System.out.println("На этот день ничего не запланировано");
                                    }
                                    break;

                                case 3:
                                    System.out.println("Введите дату в формате “DD/MM/YYYY”");
                                    stringDate = scanner.nextLine();
                                    stringDate = scanner.nextLine();

                                    while (!isDateValid(stringDate)) {
                                        System.err.println("Некорректный ввод, попробуйте еще раз!");
                                        stringDate = scanner.nextLine();
                                    }

                                    if (map.containsKey(stringDate)) {
                                        arrayOfCases = map.get(stringDate);
                                        for (int i = 0; i < arrayOfCases.size(); i++)
                                            System.out.printf("%d - %s\n", i + 1, arrayOfCases.get(i));
                                    } else
                                        System.out.println("На этот день ничего не запланировано");

                                    break;
                                case 4:
                                    /*for (Map.Entry<String, ArrayList<ListOfCases>> entry : map.entrySet()) {
                                        System.out.println(entry.getKey() + " " + entry.getValue());
                                    }*/
                                    List<String> keys = new ArrayList<>(map.keySet());

                                    if (keys.isEmpty())
                                        System.out.println("Дела не запланированы");

                                    for (int i = 0; i < keys.size(); i++) {
                                        System.out.println(keys.get(i));
                                        arrayOfCases = map.get(keys.get(i));
                                        for (int j = 0; j < arrayOfCases.size(); j++)
                                            System.out.printf("%d - %s\n", j + 1, arrayOfCases.get(j));
                                        System.out.println();
                                    }
                                    break;

                                case 5:
                                    fExit = 0;
                                    break;

                                default:
                                    System.err.println("Некорректный выбор");
                                    break;
                            }
                        } while (fExit == 3);
                    break;

                    case 4:
                        System.out.println("СПАСИБО!");
                        fExit = 1;
                        break;

                    default:
                        System.err.println("Некорректный выбор!");
                        break;
                }
            }
        } while (fExit == 0);
    }

    static Map<String,ArrayList<ListOfCases>> map = new HashMap<>();

    static List<ListOfCases> arrayOfCases = new ArrayList<>();

    public static void add (ListOfCases listOfCases, String date) {
        if (map.containsKey(date)) {
                ArrayList<ListOfCases> heap = map.get(date);

                if (heap.contains(listOfCases))
                    System.err.println("Задание уже сформировано");
                else {
                    heap.add(listOfCases);
                }
        } else {
            ArrayList<ListOfCases> heap = new ArrayList<>();
            heap.add(listOfCases);
            map.put(date, heap);
        }
    }

    public static boolean isDateValid(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void saveToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("ListOfCases.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);

            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}