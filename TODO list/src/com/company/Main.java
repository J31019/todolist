package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File ff=new File("TODO.txt");
        if (!ff.exists()){
            try {
                ff.createNewFile();
                System.out.println("Файл успешно создан");
            } catch (IOException e) {

                System.err.println("ERROR");
            }
        }
        try {
            ff.createNewFile();
        } catch (IOException e) {
            System.err.println("Что-то пошло не так" + e);
        }
        FileReader fr;
        try {
            fr = new FileReader("TODO.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Такого файла не существует");
        }
        //taskSplit=taskLine.split("$$");
        //parseToHashMap(taskSplit);
        Condition c=Condition.NOTDONE;
        ArrayList<Task> array= new ArrayList<Task>();
        Date date = new Date();
        HashMap<Date, ArrayList<Task>> dateTask= new HashMap<>();//Создаем основной TODO list
        SimpleDateFormat smd= new SimpleDateFormat("dd/MM/yyyy");
        //showTask(array);
        mainMenu(FileParser.parseLine(),array);

    }
    public static HashMap<Date, ArrayList<Task>> mainMenu(HashMap<Date, ArrayList<Task>> main, ArrayList<Task> arr){// главное меню программы
        HashMap<Date, ArrayList<Task>> temp= main;
        while (true){
            showMenu();
            int i = 4;
            try {
                i = Integer.parseInt(scanner());
            }catch (NumberFormatException e){
                System.err.println("Введите число");
                continue;
            }
            switch (i){
                case 1://Добавить задание
                    putTask(temp);
                    break;
                case 2://Выбрать задание
                    System.out.println("Выберите желаемую дату");
                    String date= scanner();
                    try {
                        main.put(stringToDate(date), menuTask(main.get(stringToDate(date))));
                    } catch (NullPointerException e){
                        System.err.println("чтото пошло не так!");
                    }
                    break;
                case 3://Показать список дел
                    menuDayTask(main);
                    break;
                case 4://Завершить работу
                    FileParser.fileWrite(main);
                    return main;
                default:
                    System.err.println("Выберите верный пункт!");
            }
        }
    }

    public static ArrayList<Date> parserDateOnWeek(){//парсер даты в виде списка дат на всю неделю
        ArrayList<Date> arrDate= new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i =2;i<8;i++){
            calendar.set(Calendar.DAY_OF_WEEK,i);
            arrDate.add(stringToDate(sdf.format(calendar.getTime())));
            //System.out.println(sdf.format(calendar.getTime()));//Вывод в консоль списка дат на неделю
        }
        calendar.add(Calendar.DAY_OF_WEEK,+1);
        arrDate.add(stringToDate(sdf.format(calendar.getTime())));
        //System.out.println(sdf.format(calendar.getTime()));//Вывод в консоль списка дат на неделю
        return arrDate;
    }
    public static void showTask(ArrayList<Task> e){//показ идекса и описание задания
        for (int i=0;i<e.size();i++){
            System.out.println();
            System.out.print("Индекс - ");
            System.out.print(e.indexOf(e.get(i)));
            System.out.print(" Задание -  ");
            System.out.print(e.get(i));
        }
    }
    public static ArrayList<Date> getDate(){//создаем список с датами
        ArrayList<Date> date=new ArrayList<>();
        Date date1=new Date();
        date.add(date1);
        return date;
    }
    public static String dateToString(Date date){//преобразование даты в красивую форму, с возможностью последующего вывода в консоль
        //(из  Date в String)
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
    public static void showTaskOnTheDay(HashMap<Date,ArrayList<Task>> main,ArrayList<Date> dates) {//вывод задания и даты в консоль
        for (int i = 0; i < dates.size(); i++) {
            ArrayList<Task> arrayList = main.get(dates.get(i));
            if (arrayList == null){
                System.out.println("\n------------------------");
                System.out.println(dateToString(dates.get(i))+ "\n" + "Нет заданий на текущую дату");
                continue;
            }else {
                for (int k = 0; k < arrayList.size(); k++) {
                        Task task = arrayList.get(k);
                        System.out.println("\n------------------------");
                        System.out.println(dateToString(dates.get(i)));
                        System.out.print(task.content);
                        System.out.print(" - ");
                        System.out.print(task.condition);
                }
            }

        }
    }
    public static HashMap<Date,ArrayList<Task>> putTask(HashMap<Date,ArrayList<Task>> main){//основное добавление в список
        System.out.println("Выберите желаемую дату");
        String date=scanner();
        System.out.println("Введите желаемое задание на выбранную дату");
        String tempTask = scanner();
        ArrayList<Task> tempList= main.get(stringToDate(date));
        if (tempList==null){
            ArrayList<Task> nList = new ArrayList<>();
            nList.add(task1(tempTask));
            main.put(stringToDate(date),nList);
            return main;
        }
        if (main.containsKey(stringToDate(date))){//проверка на наличие необходимой даты
            ArrayList<Task> temp= main.get(stringToDate(date));
            while (true) {
                for (int i = 0; i < temp.size(); i++) {
                    Task tempT = temp.get(i);
                    if (tempT.content.equals(tempTask)) {
                        System.err.println("Такое задание уже существует");
                        tempT = null;
                        System.gc();
                        System.out.println("Введите другое задание");
                        return main;
                    }
                    tempT = null;
                    System.gc();

                }
                tempList.add(task1(tempTask));
                main.put(stringToDate(date),tempList);
                System.out.println("Задание успешно добавлено");
                return main;
            }
        }
        System.out.println("Возникла какая то ошибка. Нужно разбираться.");
        return main;
    }
    public static Date stringToDate (String date){//формат даты в нужном стиле(из String в Date)
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date tempDate =  formatter.parse(date);
            return tempDate;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Введено неверное значение даты!");
            System.out.println("Введите дату в формате dd/MM/yyyy");
            String str= scanner();
            stringToDate(str);
        }
        return new Date();
    }
    @Deprecated
    public static ArrayList<Task> putTask(ArrayList<Task> tasks, String content, Condition condition){
        System.out.println("Добавление задния");
        content=scanner();
        for (int i = 0;i<tasks.size();i++) {
            Task tempT = tasks.get(i);
            if (tempT.content.equals(content)){
                System.out.println("Такое задание уже существует");
                tempT=null;
                System.gc();
                return tasks;
            }
            tempT=null;
            System.gc();
        }

        tasks.add(new Task(content,condition));
        return tasks;
    }
    public static void menuDayTask (HashMap<Date,ArrayList<Task>> main){
        while (true){
            showDayTask();
            int i=5;
            Date dateTemp = new Date();
            ArrayList<Date> arrTemp=new ArrayList<Date>();
            try {
                i = Integer.parseInt(scanner());
            }catch (NumberFormatException e){
                System.err.println("Введите число");
                continue;
            }
            switch (i){
                case 1://На сегодня
                    Date date = new Date();
                    arrTemp.add(stringToDate(dateToString(date)));
                    showTaskOnTheDay(main,arrTemp);
                    break;
                case 2://На неделю
                    showTaskOnTheDay(main,parserDateOnWeek());
                    break;
                case 3://На выбранную дату
                    arrTemp.add(stringToDate(scanner()));
                    showTaskOnTheDay(main,arrTemp);
                    break;
                case 4://Весь список
                    if (main.isEmpty()){
                        System.out.println("Список заданий пуст");
                    }
                    Set<Date> set = main.keySet();
                    Iterator<Date> iter = set.iterator();
                    while (iter.hasNext()){
                        arrTemp.add(iter.next());
                    }
                    showTaskOnTheDay(main,arrTemp);
                    break;
                case 5://Выход в главное меню
                    return;
                default:
                    System.err.println("Выберите верный пункт!");
            }
        }
    }
    public static ArrayList<Task> menuTask (ArrayList<Task> t){//меню управления задачей
        Task tempTask=takeTask(t);
        while (true){
            showMenuTask();
            int i = 5;
            try {
                i = Integer.parseInt(scanner());
            }catch (NumberFormatException e){
                System.err.println("Введите число");
                continue;
            }
            switch (i){
                case 1://Изменение описания
                    t.remove(tempTask);
                    t.add(changeTask(tempTask));
                    break;
                case 2://Удаление задачи
                    System.out.println("Вы действительно хотите удалить задание?");
                    System.err.println("y/n");
                    String g = scanner();
                    switch (g){
                        case "y":
                            t.remove(tempTask);
                            break;
                        case "n":
                            break ;
                    }
                    break;
                case 3://Пометить задание как выполненное
                    t.remove(tempTask);
                    tempTask.condition=Condition.DONE;
                    t.add(tempTask);
                    break;
                case 4://Пометить задание как невыполненное
                    t.remove(tempTask);
                    tempTask.condition=Condition.NOTDONE;
                    t.add(tempTask);
                    break;
                case 5://Выход в главное меню
                    return t;
                default:
                    System.err.println("Выберите верный пункт!");
            }
        }
    }
    /*public static Task getTask(ArrayList<Task> g){

        return ;
    }*/
    public static Task takeTask(ArrayList<Task> t){//изменение задачи по индексу
        showTask(t);
        System.out.println("\n Введите индекс задания который хотите изменить:");
        String content=scanner();
        int p=Integer.parseInt(content);
        return t.get(p);
    }
    @Deprecated
    public static ArrayList<Task> taskAfterRemove (ArrayList<Task> arrt, Task t){// удаление задачи
        arrt.remove(t);
        return arrt;
    }
    public static Task changeTask(Task task){//изменение выбранной задачи
        System.out.println("Введите новую задачу: \n");
        task.content=scanner();
        return task;
    }
    public static void showMenu(){//
        System.out.println("\n" + "Выберите нужный пункт:");
        System.out.println("1 - Добавить задание");
        System.out.println("2 - Выбрать задание");
        System.out.println("3 - Показать список дел");
        System.out.println("4 - Завершить работу");
    }
    public static void showMenuTask(){
        System.out.println("\n" +"Выберите нужный пункт:");
        System.out.println("1 - Изменить описние");
        System.out.println("2 - Удалить");
        System.out.println("3 - Пометить как выполненное");
        System.out.println("4 - Пометить как невыполненное");
        System.out.println("5 - Назад");
    }
    public static void showDayTask(){
        System.out.println("\n" +"Выберите нужный пункт:");
        System.out.println("1 - На сегодня");
        System.out.println("2 - На эту неделю");
        System.out.println("3 - На выбранную дату");
        System.out.println("4 - Показать весь список");
        System.out.println("5 - Назад");
    }
    @Deprecated
    public static void hurt(int a){

    }
    @Deprecated
    public static HashMap<Date,ArrayList<Task>> putTask(HashMap<Date,ArrayList<Task>> t , String s){
        ArrayList<Task> tempArr= new ArrayList<>();
        tempArr.add(new Task(s,Condition.NOTDONE));
        t.put(new Date(),tempArr);
        return t;
    }
    public static Task task1(String content){//создание задачи с предустановленным параметром (notedone)
        Task taskTODO = new Task(content,Condition.NOTDONE);
        return taskTODO;
    }
    public static String scanner (){//сканер
        String tempSc=null;
        Scanner sc=new Scanner(System.in);
        System.out.print("Введите значение: \n");
        tempSc = sc.nextLine();
        //sc.close();
        return tempSc;
    }
    @Deprecated
    public static void  mapTemp(){

    }

}
