package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Todolist {
    String date;
    String task;
    String sumValue;
    int numberTask;


    Scanner scanner = new Scanner(System.in);
    HashMap<String, ArrayList> list = new HashMap<>();
    HashMap<String, ArrayList> list1 = new HashMap<>();
    ArrayList arrayList = new ArrayList();
    HashMap<Integer, String> hm = new HashMap<>();
    Date date1 = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dayOfWeek = new SimpleDateFormat("u");
    Calendar calendar = new GregorianCalendar();
    File todoList = new File("To-doList.txt");

    public void add() {
        ArrayList arrayList = new ArrayList();
        Status status = Status.NOTDONE;
        while (true) {
            System.out.print("Введите дату в формате “DD/MM/YYYY” (день/месяц/год):");
            date = scanner.nextLine();
            if (date.isEmpty()) {
                System.err.print("ОШИБКА!!! ");
                System.out.println("Поле пустое!!!");
                continue;
            }
            break;
        }
        if (list.containsKey(date)) {
            arrayList = list.get(date);
            System.out.print("Запоните еще одно задание на " + date + " :");
            while (true) {
                task = scanner.nextLine();
                sumValue = task + ' ' + status.getValue();
                for (int i = 0; i < arrayList.size(); i++) {
                    String arr = (String) arrayList.get(i);
                    if (arr.equalsIgnoreCase(sumValue)) {
                        System.out.println(task + " Уже есть в списке");
                        System.out.println("Заполните уникальное задание");
                    break;

                    }
                    arrayList.add(sumValue);
                    System.out.println("Спасибо, Вы добавили еще одно задание " + task + " на " + date + ".");
                    list.put(date, arrayList);
                    return;
                }
            }
        }
            System.out.println("Спасибо, Вы ввели дату " + date + ".");
            System.out.print("Теперь напишите задание которое Вы хотите запланировать:");
        while (true){
            task = scanner.nextLine();
            if (task.isEmpty()) {
                System.err.println("ОШИБКА!!! Поле не может быть пустым!!!");
                System.out.println("Снова напишите задание которое Вы хотите запланировать:");
                continue;
            }
            break;
        }
        System.out.println("Спасибо, Вы добавили задание " + task + " на " + date + ".");
        arrayList.add(sumValue = task + ' ' + status.getValue());
        list.put(date, arrayList);

    }

    public void selectTask() {
        if (list.isEmpty()){
            System.out.println("Список пуст, запланируйте хотя бы одно дело.");
            return;
        }
        System.out.println("Введите дату (формат - “DD/MM/YYYY”)для редактирование списка заданий на этот день.");
        while (true) {
            date = scanner.nextLine();
            if (list.containsKey(date)) {
                transfer();
                while (true) {
                    System.out.println("Введите порядковый номер дела, которое Вы хотите редактировать.");
                    numberTask = scanner.nextInt();
                    if (!hm.containsKey(numberTask)) {
                        System.out.println("№ " + numberTask + " - отсутствует в списке дел указанных выше");
                        continue;
                    }
                    menu2();
                    return;
                }
            }
            System.out.println("На " + date + " нет запланированных дел.");
            System.out.println("Снова напишите дату:");
            continue;
        }
    }
    //Новое описание
    public void newDescription(){
        while (true){
            Status status = Status.NOTDONE;
            System.out.println("Введите новое описание:");
            String nd = scanner.nextLine();
            sumValue = nd + " " + status.getValue();
            if (hm.containsValue(sumValue)){
                System.out.println(nd + " - уже есть в списке");
                continue;
            }
            hm.put(numberTask,sumValue);
            System.out.println("Новое описание успешно сохранено");
            recordHMinAL();
            transfer();
            break;
        }
    }
    //Удаление
    public void remove(){
        System.out.println("Вы действительно хотите удалить выбранное дело? (y/n)");
        while (true){
            char yn = scanner.next().charAt(0);
        switch (yn) {
            case 'y':
                hm.remove(numberTask);
                recordHMinAL();
                System.out.println("Задание успешно удалено.");
                if (hm.isEmpty()) {
                    list.remove(date);
                    System.out.println("Теперь на " + date + " дела отсутствуют.");
                    return;
                }
                transfer();
            case 'n':
                System.out.println("Задание не удалено.");
                return;
            default:
                System.err.println("Не правильно сделан выбор.\nВведите y или n:");
        }

    }

    }
    //Пометить как выполненное
    public void statusDONE(){
        Status status = Status.DONE;
        task = hm.get(numberTask);
        sumValue = task.replace("Невыполнено",status.getValue());
        hm.put(numberTask,sumValue);
        System.out.println("Вы успешно изменили статус.");
        recordHMinAL();
        transfer();

    }
    //Пометить как невыполненное
    public void statusNOTDONE(){
        Status status = Status.NOTDONE;
        task = hm.get(numberTask);
        sumValue = task.replace("Выполнено",status.getValue());
        hm.put(numberTask,sumValue);
        System.out.println("Вы успешно изменили статус.");
        recordHMinAL();
        transfer();

    }

    //Перечисление дел на указанную дату
    public void transfer(){
        System.out.println("На " + date + " у Вас следующий список дел:");
        hm.clear();
        arrayList = list.get(date);
        for (int i = 0; i < arrayList.size(); i++) {
            int a = i + 1;
            task = (String) arrayList.get(i);
            hm.put(a, task);
        }
        for (Map.Entry<Integer, String> pair : hm.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
       }
        System.out.println();
    }

    //Запись из HashMap в ArrayList
    public void recordHMinAL(){
        arrayList.clear();
        for (Integer key: hm.keySet()){
            arrayList.add(hm.get(key));
        }
        list.put(date,arrayList);
    }

    //Меню к пункту №2
    public void menu2() {
        while (true) {
            System.out.println("\nТеперь выберите действие:\n1) Изменить описание\n2) Удалить" +
                    "\n3) Пометить как выполненное\n4) Пометить как невыполненное\n5) Отмена (возврат в главное меню)");
            switch (getNumber()) {
                case 1:
                    newDescription();
                    return;
                case 2:
                    remove();
                    return;
                case 3:
                    statusDONE();
                    return;
                case 4:
                    statusNOTDONE();
                    return;
                case 5:
                    System.out.println("Возврат в главное меню");
                    return;
                default:
                    System.err.println("Не правильно сделан выбор.\nВведите № из указанного списка.");
                    continue;
            }
        }
    }

    //Меню к пункту №3
    public void menu3(){
        while (true){
        System.out.println("\nВыберите проядковый № для отобразения списка дел:\n1) На сегодня\n2) На эту неделю" +
                "\n3) На выбранную дату\n4) Весь список\n5) Назад (возврат в главное меню)");
        switch (getNumber()) {
            case 1:
                currentDate();
                return;
            case 2:
                showTaskWeek();
                return;
            case 3:
                selectedDate();
                return;
            case 4:
                showTodoList();
                return;
            case 5:
                System.out.println("Возврат в главное меню");
                return;
            default:
                System.err.println("Не правильно сделан выбор.\nВведите № из указанного списка.");
                continue;
            }
        }
    }
    //Выводим список дел на текущую дату
    public void currentDate (){
        date = (dateFormat.format(date1));
        if(list.containsKey(date)){
            transfer();
            return;
        }
        System.out.println("На "+ date + " заданий не запланировано");
    }
    //Выводим список дел на текущую неделю
    public void showTaskWeek() {
        list1.clear();
        int d = 8 - Integer.parseInt((dayOfWeek.format(date1)));
        for (int i = 0; i < d; i++) {
            calendar.setTime(date1);
            calendar.add(calendar.DAY_OF_MONTH, i);
            date = dateFormat.format(calendar.getTime());
            if (list.containsKey(date)) {
                list1.put(date, list.get(date));
                continue;
            }
            continue;
        }
        if (list1.isEmpty()) {
            System.out.println("У вас не запланировано дел на текущую неделю");
            return;
        }
        for (Map.Entry<String, ArrayList> pair : list1.entrySet()) {
            arrayList = list1.get(pair.getKey());
            System.out.println(' ');
            System.out.println("На " + pair.getKey() + " у Вас следующий список дел:");
            hm.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                int a = i + 1;
                task = (String) arrayList.get(i);
                hm.put(a, task);

            }
            for (Map.Entry<Integer, String> pair1 : hm.entrySet()) {
                System.out.println(pair1.getKey() + " - " + pair1.getValue());
            }
        }
    }
    //Выводим список дел на выбранную дату
    public void selectedDate() {
        System.out.print("Введите дату в формате “DD/MM/YYYY” (день/месяц/год):");
        date = scanner.nextLine();
        if (list.containsKey(date)) {
            transfer();
            return;
        }
        System.out.println("На "+ date + " заданий не запланировано");
    }



    //Выводим весь список дел
    public void showTodoList () {
        if (list.isEmpty()){
            System.out.println("Список пуст, запланируйте хотя бы одно дело.");
            return;
        }
        for (Map.Entry<String, ArrayList> pair : list.entrySet()) {
            arrayList = list.get(pair.getKey());
            System.out.println(' ');
            System.out.println("На " + pair.getKey() + " у Вас следующий список дел:");
            hm.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                int a = i + 1;
                task = (String) arrayList.get(i);
                hm.put(a, task);

            }
            for (Map.Entry<Integer, String> pair1 : hm.entrySet()) {
                System.out.println(pair1.getKey() + " - " + pair1.getValue());
            }
        }
    }
    //создаем файл
    public void createFile() throws IOException {
        if (!todoList.exists()) {
            todoList.createNewFile();
        }
        readerFile();
    }
    //Записываем в файл
    public void writeFile() throws IOException {
        PrintWriter pw = new PrintWriter(todoList);
        for (Map.Entry<String, ArrayList> pair : list.entrySet()) {
            arrayList = list.get(pair.getKey());
            for (int i = 0; i < arrayList.size(); i++) {
                sumValue = pair.getKey() + "@#@" + arrayList.get(i);
                pw.println(sumValue);
            }
        }
        pw.close();
    }
    //Читаем Файл и записываем в List
    public void readerFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("To-doList.txt"));
        while (br.ready()) {
            ArrayList arrayList = new ArrayList();
            String[] str = br.readLine().split("@#@");
            date = (str[0]);
            sumValue = (str[1]);
            if (list.containsKey(date)) {
                arrayList = list.get(date);
                arrayList.add(sumValue);
            }else {
                arrayList.add(sumValue);
            }
            list.put(date, arrayList);
       }
    }




    public int getNumber(){
        Scanner scanner1 = new Scanner(System.in);
        int i ;
        try {
            System.out.println("Введите №:");
            i = Integer.parseInt(scanner1.nextLine());
            if (i > 0) {
                return i;
            }
        } catch (NumberFormatException e) {
        }
        return 0;

    }

}
