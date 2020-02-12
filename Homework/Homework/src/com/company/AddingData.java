package com.company;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddingData implements Serializable {
    HashMap<String, ArrayList<Filling>> dateTaskStatus = new HashMap<>();
    transient Scanner sc = new Scanner(System.in);
    transient Scanner ss = new Scanner(System.in);
    ArrayList<String> set = new ArrayList<>();

    public void addDate(String date, String task, String status) {
        Filling entry = new Filling(task, status);
        if (dateTaskStatus.containsKey(date)) {
            if (findTask(task,date)) {
                System.out.println("Такое задание уже есть");
            } else {
                dateTaskStatus.get(date).add(entry);
            }
        } else {
            ArrayList<Filling> innerContent = new ArrayList<>();
            innerContent.add(entry);
            dateTaskStatus.put(date, innerContent);
        }
    }
    public void changeData() throws ParseException {
        try {
            showMeKeys();
            String date = enterDate();
            if (date != null) {
                if(showMeKeys(date)){
                System.out.println("Введите номер дела");
                int option = sc.nextInt();
                if (option > dateTaskStatus.get(date).size() || option < 1) {
                    System.out.println("Задания с таким номером не существует. Просим повторить ввод.");
                    return;
                } else {
                    System.out.println("1) Изменить описание\n2) Удалить\n3) Пометить как выполненное\n4) Пометить как невыполненное\n5) Отмена");
                    Scanner ss = new Scanner(System.in);
                    String changeOption = ss.nextLine();
                    switch (changeOption) {
                        case "1":
                            System.out.println("Введите новое описание");
                            String newTask = ss.nextLine();
                            if (findTask(newTask, date)) {
                                System.out.println("Такое задание уже есть");
                            } else {
                                dateTaskStatus.get(date).get(option - 1).task = newTask;
                            }
                            break;
                        case "2":
                            System.out.println("Вы действительно хотите удалить выбранное дело? (y/n)");
                            newTask = ss.nextLine();
                            switch (newTask) {
                                case "y":
                                    if (dateTaskStatus.get(date).size() == 1) {
                                        dateTaskStatus.remove(date);
                                    }else{
                                        dateTaskStatus.get(date).remove(option - 1);
                                    }
                                    break;
                                case "n":
                                    System.out.println("Возвращение в главное меню");
                                    break;
                                default:
                                    System.out.println("Ошибка ввода.");
                            }
                            break;
                        case "3":
                            dateTaskStatus.get(date).get(option - 1).status = Status.ISDONE.getStatus();
                            break;
                        case "4":
                            if (dateTaskStatus.size()==0){
                                System.out.println("Заданий нет");
                            }else {
                            dateTaskStatus.get(date).get(option - 1).status = Status.ISNOTDONE.getStatus();}
                            break;
                        default:
                            System.out.println("Ошибка ввода");
                        case "5":
                    }
                    }
                    }else{
                    System.out.println("На эту дату нет заданий");
                    return;
                }
                }
            else {
                System.out.println("Ошибка ввода");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода номера задания");
            return;
        }
    }
    public void showData() throws ParseException {
        System.out.println("1) На сегодня\n2) На эту неделю\n3) На выбранную дату\n4) Весь список\n5) Назад");
        String sd = ss.nextLine();
        String date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("/MM/yyyy");
        String date1 = dateFormat1.format(new Date());
        String date3 = dateFormat2.format(new Date());
        String date4 = dateFormat.format(new Date());
        int d = Integer.parseInt(date1);
        switch (sd) {
            case "1":
                if (showMeKeys(date4)){
                    showMeData(date4);
                }else{
                    System.out.printf("%s - [Нет заданий]\n",date4);
                }

                break;
            case "2":
                for (int i = 0; i < 7; i++) {
                    date = Integer.toString(d);
                    date = date.concat(date3);
                    ++d;
                    if (showMeKeys(date)){
                        showMeData(date);
                    }else {
                        System.out.printf("%s - [Нет заданий]\n", date);
                    }
                }
                break;
            case "3":
                String date2 = enterDate();
                if (date2 != null) {
                    if (showMeKeys(date2)) {
                        showMeData(date2);
                    } else {
                        System.out.printf("%s - [Нет заданий]\n", date2);
                        break;
                    }
                }else{
                    System.out.println("Ошибка ввода");
                break;
                }
            case "4":
                if (dateTaskStatus.size()==0){
                    System.out.println("Нет заданий.");
                    break;
                }else {
                    showMeKeys();
                }
                break;
            default:
                System.out.println("Ошибка ввода");
            case "5":
        }
    }
    public String enterDate() throws ParseException {
        System.out.println("Введите дату в формате DD/MM/YYYY:");
        Scanner scs = new Scanner(System.in);
        String date = scs.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(dateFormat.parse(date));
        String date2 = dateFormat.format(c.getTime());
        if (date.equals(date2)) {
            return date2;
        }else{
            System.out.println("Ошибка ввода даты. Возвращение в главное меню");
            menu();
            return null;}
    }
    public boolean findTask(String task, String date){
        for (int i = 0; i < dateTaskStatus.get(date).size(); i++){
            if(task.equals(dateTaskStatus.get(date).get(i).task)){
                return true;
            }
        }return false;
    }
    public void showMeData(String date){
        System.out.println(date);
        for (int f = 1;f < dateTaskStatus.get(date).size() + 1;f++){
            System.out.printf(" - [%d > %s = %s]",f,dateTaskStatus.get(date).get(f-1).task, dateTaskStatus.get(date).get(f-1).status);
        }
        System.out.println("\n");
    }
    public void showMeKeys(){
        for (int i = 0; i < dateTaskStatus.size(); i++) {
            set.addAll(dateTaskStatus.keySet());
            showMeData(set.get(i));
            set.clear();
        }
    }
    public boolean showMeKeys(String date){
        set.addAll(dateTaskStatus.keySet());
        for (int i = 0; i < set.size();i++ ){
            if (date.equals(set.get(i))){
                set.clear();
                return true;
            }
        }set.clear();
        return false;
    }
    public void menu(){
        String date;
        try {
            for (; ; ) {
                System.out.println("1) Добавить задание\n2) Выбрать задание\n3) Показать список дел\n4) Завершить работу");
                String s = ss.nextLine();
                switch (s) {
                    case "1":
                        date = enterDate();
                        if (date == null){
                            return;
                        }
                        System.out.println("Введите описание задания");
                        String task = ss.nextLine();
                        String status = Status.ISNOTDONE.getStatus();
                        addDate(date, task, status);
                        break;
                    case "2":
                        changeData();
                        break;
                    case "3":
                        showData();
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Неправильное значение ввода. Просьба повторить ввод");
                }
            }
        } catch (ParseException e) {
            System.out.println("Ошибка парсера. Переход в главное меню");
            menu();
        }
    }
}