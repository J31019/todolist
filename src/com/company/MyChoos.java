package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.MyFile.openFile;


public class MyChoos {
    private static boolean bool;
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, HashMap> list;
    private static HashMap<String, TaskImplementation> list1;

    static {
        try {
            list = openFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Выводим содержимое коллекции на текущую дату или на требуемую
    public static void showTaskToDay(String date) throws IOException {
        if (list.containsKey(date)) {
            bool = true;
            indexPrint2(date);
        } else {
            System.out.printf("%-20s заданий нет.\n", date);
        }
    }

    // Выводим все содержимое коллекции
    public static void showTaskAll() throws IOException {
        if (!list.isEmpty()) {
            bool = true;
            for (String entry : list.keySet()) {
                indexPrint2(entry);
            }
        } else {
            System.out.println("Cписок пуст.");
        }
    }

    //Выводим коллекцию на текущую неделю
    public static void showTaskWeek() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.roll(Calendar.DATE,-(Calendar.DAY_OF_WEEK));
        bool = true;
        for (int i = 1; i < 8; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            String date = simpleDateFormat.format(calendar.getTime());
            if (list.containsKey(date)) {
                indexPrint2(date);
            } else {
                if (bool)
                    System.out.printf("   %1$-15s %2$-23s %3$s\n%4$-15s %5$-16s %6$s\n",
                            "  Дата", "Статус выполнения", "Задание",
                            "--------------", "---------------------", "-------------------");
                System.out.printf("%-20s заданий нет.\n", date);
                bool = false;
            }
        }
    }

    //Добавить задание
    public static void List() throws ParseException, IOException {
        HashMap<String, TaskImplementation> list1 = new HashMap<>();
        String data = MyDate.toDate(1);
        if (!list.containsKey(data)) {
            list.put(data, arList(list1));
        } else {
            list.put(data, arList(list.get(data)));
        }
        MyFile.toFile(list);
    }

    //Fixme - -- для нахождения
    public static HashMap arList(HashMap<String, TaskImplementation> list2) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите задание: \r");
            String task = scanner.nextLine();
            if (!list2.containsKey(task)) {
                list2.put(task, TaskImplementation.FALSE);
                System.out.println("Задание добавлено успешно.\n");
                return list2;
            } else {
                System.out.println("Данная задача уже существует.");
            }
        }
    }

    //Пометить задание, как не выполнена
    public static void getСhangeFalse(String date) throws IOException {
        int i = 0;
        if (list.containsKey(date)) {
            list1 = list.get(date);
            i = indexPrint(i, date);
            System.out.println("Введите номер задания для изменения");
            int ii = scanner.nextInt();
            if (ii <= i) {
                i = 0;
                for (String entry : list1.keySet()) {
                    if (ii == ++i) {
                        list1.put(entry, TaskImplementation.FALSE);
                    }
                }
                list.put(date, list1);
                MyFile.toFile(list);
            } else {
                System.out.println("Данная позиция отсутствует.");
            }
        } else {
            System.out.printf("%-20s заданий нет.\n", date);
        }
    }

    //Пометить задание, как выполенное.
    public static void getСhangeTrue(String date) throws IOException {
        int i = 0;
        if (list.containsKey(date)) {
            list1 = list.get(date);
            i = indexPrint(i, date);
            System.out.println("Введите номер задания для изменения");
            int ii = scanner.nextInt();
            if (ii <= i) {
                i = 0;
                for (String entry : list1.keySet()) {
                    if (ii == ++i) {
                        list1.put(entry, TaskImplementation.TRUE);
                    }
                }
                list.put(date, list1);
                MyFile.toFile(list);
            } else {
                System.out.println("Данная позиция отсутствует.");
            }
        } else {
            System.out.printf("%-20s заданий нет.\n", date);
        }
    }

    // Удаляем задачу
    public static void getСhangeRemove(String date) throws IOException, ParseException {
        int i = 0;

        if (list.containsKey(date)) {
            list1 = list.get(date);
            i = indexPrint(i, date);
            System.out.println("Введите номер задания для удаления.");
            int ii = MyScaner.getScanerInt();

            if (ii <= i) {
                if (getDell()) {
                    i = 0;
                    for (String entry : list1.keySet()) {
                        if (ii == ++i) {
                            list1.remove(entry);
                        }
                        if (!list1.isEmpty()) {
                            list.remove(date);
                        }
                    }

                    System.out.println("Данное задание удалено.");
                    list.put(date, list1);
                    MyFile.toFile(list);
                } else {
                    System.out.println("Данное задание не удалено.");
                }
            } else {
                System.out.println("Данное задание отсутствует.");
            }
        } else {
            System.out.printf("%-20s заданий нет.\n", date);
        }
    }

    // Меняем описание задачи
    public static void getСhangeFix(String date) throws IOException {
        String str = "";
        int i = 0;

        if (list.containsKey(date)) {
            list1 = list.get(date);
            i = indexPrint(i, date);
            System.out.println("Введите номер задания для изменения");
            int ii = scanner.nextInt();
            if (ii <= i && ii > 0) {
                i = 0;
                for (String entry : list1.keySet()) {
                    if (ii == ++i) {
                        str = entry;
                        break;
                    }
                }
                TaskImplementation taskIp = list1.get(str);
                while (true) {
                    System.out.print("Введите изменение к заданию: \r");
                    String string = scanString();
                    if (!list1.containsKey(string)) {
                        list1.remove(str);
                        list1.put(string, taskIp);
                        break;
                    } else {
                        System.out.println("Данная задача уже существует.");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Данная позиция отсутствует.");
            }
            list.put(date, list1);
            MyFile.toFile(list);
        } else {
            System.out.printf("%-20s заданий нет.\n", date);
        }
    }


    public static String scanString() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        return str;
    }


    public static int indexPrint(int i, String date) {
        bool = true;
        for (String entry : list1.keySet()) {
            if (bool) {
                System.out.printf("  %1$-10s %2$-13s %3$-25s %4$s\n%5$-5s %6$-6s %7$-25s %8$s\n"
                        , "№", "Дата", "Статус выполнения", "Задание",
                        "------", "--------------", "---------------------", "-------------------");
                bool = false;
            }
            System.out.printf("  %1$-7s %2$-18s %3$-20s %4$s\n", ++i, date, list1.get(entry).getStatus(), entry);
        }
        return i;

    }


    public static void indexPrint2(String date) {
        list1 = list.get(date);
        for (String entry : list1.keySet()) {
            if (bool) {
                System.out.printf("   %1$-15s %2$-23s %3$s\n%4$-15s %5$-16s %6$s\n",
                        "  Дата", "Статус выполнения", "Задание",
                        "--------------", "---------------------", "-------------------");
                bool = false;
            }
            System.out.printf("%1$-20s %2$-20s %3$s\n", date, list1.get(entry).getStatus(), entry);
        }
    }


    public static boolean getDell() {
        boolean bool;
        System.out.println("Вы действительно хотите\n" +
                "удалить выбранное задание? (y/n)");
        char ch = new Scanner(System.in).next().charAt(0);
        if (ch == 'y') {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }
}