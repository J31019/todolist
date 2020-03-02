<<<<<<< HEAD
package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Methods {
    static HashMap<Object, HashMap<String,Status>> toDoList = new HashMap<Object, HashMap<String,Status>>();
    private Date date1 = new Date();
    private  Task task = new Task();
 HashMap<String, Status> mapIn = new HashMap<String, Status>();


//Добавить задание
     public void addTimeTask() throws ParseException {
         task.status = Status.NotDone;
         Scanner sc1 = new Scanner(System.in);
         boolean a = true;
         while( a == true){
             try{
                 System.out.println("Введите дату (dd/mm/yyyy): ");
                 date1.date = sc1.nextLine();
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                 java.util.Date d = sdf.parse(date1.date);
                 String f =sdf.format(d);
                a = false;

           } catch (ParseException e) {
               System.out.println("Неккоректно введена дата!!!");
            }

        }
        System.out.println("Введите описание задания: ");
        task.description = sc1.nextLine();
        if (toDoList.containsKey(date1.date)){
             HashMap<String,Status> mapIn = toDoList.get(date1.date);
             if (mapIn.containsKey(task.description)){
                 System.out.println("Такое задание уже запланиравано на эту дату.");
             }
             else {
                 mapIn.put(task.description, task.status);
                 toDoList.put(date1.date, mapIn);
             }
        }
        else {
            mapIn.put(task.description, task.status);
            toDoList.put(date1.date, mapIn);
        }
    }

//Показать список дел
   public void showToDoList() throws ParseException {
       System.out.println("1) На сегодня");
       System.out.println("2) На эту неделю");
       System.out.println("3) На выбранную дату");
       System.out.println("4) Весь список");
       System.out.println("5) Назад");
        Scanner sc5 = new Scanner(System.in);
        int menuForShowTDL = sc5.nextInt();
        switch (menuForShowTDL){
            case 1:
                System.out.println("Список дел на сегодня: ");
                java.util.Date DateToday = new java.util.Date();
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println(sdf2.format(DateToday));                             //проверка
                if (toDoList.containsKey(sdf2.format(DateToday))){
                    System.out.println(toDoList.get(sdf2.format(DateToday)));
                }
                else{
                    System.out.println("На сегодня ничего не заплапнировано");
                }
                break;
            case 2:
                System.out.println("Список дел на эту неделю: ");
                Calendar c = GregorianCalendar.getInstance();
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                c.add(Calendar.DATE,0 );
                String day = df.format(c.getTime());
                if (toDoList.containsKey(day)){
                    System.out.println(day +" " + toDoList.get(day));
                }
                for(int i = 0; i < 6;i++){
                    c.add(Calendar.DATE,1 );
                    day = df.format(c.getTime());
                    if (toDoList.containsKey(day)){
                        System.out.println(day +" " + toDoList.get(day));
                    }
                }
                System.out.println("Дел на эту неделю не запланировано.");
                break;
            case 3:
                try {
                System.out.println("Введите необходимую дату в формате DD/MM/YYYY: ");
                Scanner sc6 = new Scanner(System.in);
                Date dateShowTaskOnDate = new Date();
                dateShowTaskOnDate.date = sc6.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                java.util.Date d = sdf.parse(dateShowTaskOnDate.date);
                String f =sdf.format(d);
                if (toDoList.containsKey(dateShowTaskOnDate.date)){
                    System.out.println("Список дел на " + " " + dateShowTaskOnDate.date + toDoList.get(dateShowTaskOnDate.date));
                }
                else{
                    System.out.println("Дел на " + dateShowTaskOnDate.date + " НЕ ЗАПЛАНИРАВАНО");
                }
                } catch (ParseException e) {
                    System.out.println("Неккоректно введена дата!!!");
                }


                break;
            case 4:
                if(toDoList.isEmpty()){
                    System.out.println("Ваш список дел ПУСТ.");
                }
                else {
                System.out.println("Весь список: ");
                for (Map.Entry entry: toDoList.entrySet()){
                        System.out.println(entry);
                    }
                }
                break;
            case 5:
                break;
        }
   }

//Выбрать задание
    public void chooseTask() throws ParseException{
         try {
             System.out.println("Введите дату в формате DD/MM/YYYY.");
             Scanner sc2 = new Scanner(System.in);
             Date dateForChTask = new Date();
             dateForChTask.date = sc2.nextLine();
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
             java.util.Date d = sdf.parse(dateForChTask.date);
             String f = sdf.format(d);

             if (toDoList.containsKey(dateForChTask.date)) {
                 HashMap<String, Status> mapaForList = new HashMap<>();
                 mapaForList = toDoList.get(dateForChTask.date);
                 ArrayList<String> spisokTask = new ArrayList(mapaForList.keySet());
                 ArrayList<Status> spisokStatus = new ArrayList(mapaForList.values());
                 for (int i = 0; i < spisokTask.size(); i++) {
                     System.out.println(i + 1 + "   " + spisokTask.get(i) + "   " + spisokStatus.get(i));
                 }

                 System.out.println("Введите порядковый номер дела, которое вы хотите отредактировать: ");
                 int nTaskForRedact = sc2.nextInt();
                 System.out.println(spisokTask.get(nTaskForRedact - 1) + "   " + spisokStatus.get(nTaskForRedact - 1));
                 System.out.println("1. Изменить описание.");
                 System.out.println("2. Удалить");
                 System.out.println("3. Пометить как выполненное.");
                 System.out.println("4. Пометьть как невыполненное.");
                 System.out.println("5. Отмена.");
                 int nMenuForRedact = sc2.nextInt();
                 switch (nMenuForRedact) {
                     case 1:
                         System.out.println("Введите новое описание задания: ");
                         Scanner sc3 = new Scanner(System.in);
                         String newTaskForRedact = sc3.nextLine();
                         System.out.println("test");
                         HashMap<String, Status> mapIn = toDoList.get(dateForChTask.date);
                         Status statusForCh1 = mapIn.get(spisokTask.get(nTaskForRedact - 1));
                         mapIn.remove(spisokTask.get(nTaskForRedact - 1));
                         task.status = statusForCh1;
                         mapIn.put(newTaskForRedact, task.status);
                         toDoList.put(dateForChTask.date, mapIn);
                         break;
                     case 2:
                         System.out.println("Вы действительно хотите удалить выбранное дело? (y/n)");
                         Scanner sc4 = new Scanner(System.in);
                         String yesNo = sc4.nextLine();
                         String y = "y";
                         String n = "n";
                         if (yesNo.equals(y)) {

                             mapIn = toDoList.get(dateForChTask.date);
                             mapIn.remove(spisokTask.get(nTaskForRedact - 1));
                             if (mapIn.isEmpty()) {
                                 toDoList.remove(dateForChTask.date);
                             }
                             System.out.println("Выбранное дело удалено из списка.");
                         } else if (yesNo.equals(n)) {
                             System.out.println("Выбранное дело НЕ УДАЛЕНО из списка.");
                         } else {
                             System.out.println("Введите -y- либо -n-!");
                         }
                         break;
                     case 3:
                         mapIn = toDoList.get(dateForChTask.date);
                         Status statusForCh3 = mapIn.get(spisokTask.get(nTaskForRedact - 1));
                         if (statusForCh3 == Status.Done) {
                             System.out.println("Это дело УЖЕ ВЫЫПОЛНЕННО");
                         } else {
                             mapIn.put(spisokTask.get(nTaskForRedact - 1), Status.Done);
                             System.out.println("Дело " + spisokTask.get(nTaskForRedact - 1) + " помечено в списке как ВЫПОЛНЕННО.");
                         }
                         break;
                     case 4:
                         mapIn = toDoList.get(dateForChTask.date);
                         Status statusForCh4 = mapIn.get(spisokTask.get(nTaskForRedact - 1));
                         if (statusForCh4 == Status.Done) {
                             mapIn.put(spisokTask.get(nTaskForRedact - 1), Status.NotDone);
                             System.out.println("Дело " + spisokTask.get(nTaskForRedact - 1) + " помечено в списке как НЕВЫПОЛНЕННОЕ.");
                         } else {
                             System.out.println("Это дело УЖЕ отмечено в списке как НЕВЫЫПОЛНЕННОЕ");
                         }
                         break;
                     case 5:
                         break;
                     default:
                         System.out.println("Неккоректно введён номер.");
                         break;

                 }
             }
             else {
                 System.out.println("Мероприятий на эту дату не заплапнировано");
             }
         } catch (ParseException e) {
             System.out.println("Неккоректно введена дата!!!");
         }
    }
}
=======
package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Methods {
    static HashMap<Object, HashMap<String,Status>> toDoList = new HashMap<Object, HashMap<String,Status>>();
    private Date date1 = new Date();
    private  Task task = new Task();
 HashMap<String, Status> mapIn = new HashMap<String, Status>();


//Добавить задание
     public void addTimeTask() throws ParseException {
         task.status = Status.NotDone;
         Scanner sc1 = new Scanner(System.in);
         boolean a = true;
         while( a == true){
             try{
                 System.out.println("Введите дату (dd/mm/yyyy): ");
                 date1.date = sc1.nextLine();
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                 java.util.Date d = sdf.parse(date1.date);
                 String f =sdf.format(d);
                a = false;

           } catch (ParseException e) {
               System.out.println("Неккоректно введена дата!!!");
            }

        }
        System.out.println("Введите описание задания: ");
        task.description = sc1.nextLine();
        if (toDoList.containsKey(date1.date)){
             HashMap<String,Status> mapIn = toDoList.get(date1.date);
             if (mapIn.containsKey(task.description)){
                 System.out.println("Такое задание уже запланиравано на эту дату.");
             }
             else {
                 mapIn.put(task.description, task.status);
                 toDoList.put(date1.date, mapIn);
             }
        }
        else {
            mapIn.put(task.description, task.status);
            toDoList.put(date1.date, mapIn);
        }
    }

//Показать список дел
   public void showToDoList() throws ParseException {
       System.out.println("1) На сегодня");
       System.out.println("2) На эту неделю");
       System.out.println("3) На выбранную дату");
       System.out.println("4) Весь список");
       System.out.println("5) Назад");
        Scanner sc5 = new Scanner(System.in);
        int menuForShowTDL = sc5.nextInt();
        switch (menuForShowTDL){
            case 1:
                System.out.println("Список дел на сегодня: ");
                java.util.Date DateToday = new java.util.Date();
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println(sdf2.format(DateToday));                             //проверка
                if (toDoList.containsKey(sdf2.format(DateToday))){
                    System.out.println(toDoList.get(sdf2.format(DateToday)));
                }
                else{
                    System.out.println("На сегодня ничего не заплапнировано");
                }
                break;
            case 2:
                System.out.println("Список дел на эту неделю: ");
                Calendar c = GregorianCalendar.getInstance();
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                c.add(Calendar.DATE,0 );
                String day = df.format(c.getTime());
                if (toDoList.containsKey(day)){
                    System.out.println(day +" " + toDoList.get(day));
                }
                for(int i = 0; i < 6;i++){
                    c.add(Calendar.DATE,1 );
                    day = df.format(c.getTime());
                    if (toDoList.containsKey(day)){
                        System.out.println(day +" " + toDoList.get(day));
                    }
                }
                System.out.println("Дел на эту неделю не запланировано.");
                break;
            case 3:
                try {
                System.out.println("Введите необходимую дату в формате DD/MM/YYYY: ");
                Scanner sc6 = new Scanner(System.in);
                Date dateShowTaskOnDate = new Date();
                dateShowTaskOnDate.date = sc6.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                java.util.Date d = sdf.parse(dateShowTaskOnDate.date);
                String f =sdf.format(d);
                if (toDoList.containsKey(dateShowTaskOnDate.date)){
                    System.out.println("Список дел на " + " " + dateShowTaskOnDate.date + toDoList.get(dateShowTaskOnDate.date));
                }
                else{
                    System.out.println("Дел на " + dateShowTaskOnDate.date + " НЕ ЗАПЛАНИРАВАНО");
                }
                } catch (ParseException e) {
                    System.out.println("Неккоректно введена дата!!!");
                }


                break;
            case 4:
                if(toDoList.isEmpty()){
                    System.out.println("Ваш список дел ПУСТ.");
                }
                else {
                System.out.println("Весь список: ");
                for (Map.Entry entry: toDoList.entrySet()){
                        System.out.println(entry);
                    }
                }
                break;
            case 5:
                break;
        }
   }

//Выбрать задание
    public void chooseTask() throws ParseException{
         try {
             System.out.println("Введите дату в формате DD/MM/YYYY.");
             Scanner sc2 = new Scanner(System.in);
             Date dateForChTask = new Date();
             dateForChTask.date = sc2.nextLine();
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
             java.util.Date d = sdf.parse(dateForChTask.date);
             String f = sdf.format(d);

             if (toDoList.containsKey(dateForChTask.date)) {
                 HashMap<String, Status> mapaForList = new HashMap<>();
                 mapaForList = toDoList.get(dateForChTask.date);
                 ArrayList<String> spisokTask = new ArrayList(mapaForList.keySet());
                 ArrayList<Status> spisokStatus = new ArrayList(mapaForList.values());
                 for (int i = 0; i < spisokTask.size(); i++) {
                     System.out.println(i + 1 + "   " + spisokTask.get(i) + "   " + spisokStatus.get(i));
                 }

                 System.out.println("Введите порядковый номер дела, которое вы хотите отредактировать: ");
                 int nTaskForRedact = sc2.nextInt();
                 System.out.println(spisokTask.get(nTaskForRedact - 1) + "   " + spisokStatus.get(nTaskForRedact - 1));
                 System.out.println("1. Изменить описание.");
                 System.out.println("2. Удалить");
                 System.out.println("3. Пометить как выполненное.");
                 System.out.println("4. Пометьть как невыполненное.");
                 System.out.println("5. Отмена.");
                 int nMenuForRedact = sc2.nextInt();
                 switch (nMenuForRedact) {
                     case 1:
                         System.out.println("Введите новое описание задания: ");
                         Scanner sc3 = new Scanner(System.in);
                         String newTaskForRedact = sc3.nextLine();
                         System.out.println("test");
                         HashMap<String, Status> mapIn = toDoList.get(dateForChTask.date);
                         Status statusForCh1 = mapIn.get(spisokTask.get(nTaskForRedact - 1));
                         mapIn.remove(spisokTask.get(nTaskForRedact - 1));
                         task.status = statusForCh1;
                         mapIn.put(newTaskForRedact, task.status);
                         toDoList.put(dateForChTask.date, mapIn);
                         break;
                     case 2:
                         System.out.println("Вы действительно хотите удалить выбранное дело? (y/n)");
                         Scanner sc4 = new Scanner(System.in);
                         String yesNo = sc4.nextLine();
                         String y = "y";
                         String n = "n";
                         if (yesNo.equals(y)) {

                             mapIn = toDoList.get(dateForChTask.date);
                             mapIn.remove(spisokTask.get(nTaskForRedact - 1));
                             if (mapIn.isEmpty()) {
                                 toDoList.remove(dateForChTask.date);
                             }
                             System.out.println("Выбранное дело удалено из списка.");
                         } else if (yesNo.equals(n)) {
                             System.out.println("Выбранное дело НЕ УДАЛЕНО из списка.");
                         } else {
                             System.out.println("Введите -y- либо -n-!");
                         }
                         break;
                     case 3:
                         mapIn = toDoList.get(dateForChTask.date);
                         Status statusForCh3 = mapIn.get(spisokTask.get(nTaskForRedact - 1));
                         if (statusForCh3 == Status.Done) {
                             System.out.println("Это дело УЖЕ ВЫЫПОЛНЕННО");
                         } else {
                             mapIn.put(spisokTask.get(nTaskForRedact - 1), Status.Done);
                             System.out.println("Дело " + spisokTask.get(nTaskForRedact - 1) + " помечено в списке как ВЫПОЛНЕННО.");
                         }
                         break;
                     case 4:
                         mapIn = toDoList.get(dateForChTask.date);
                         Status statusForCh4 = mapIn.get(spisokTask.get(nTaskForRedact - 1));
                         if (statusForCh4 == Status.Done) {
                             mapIn.put(spisokTask.get(nTaskForRedact - 1), Status.NotDone);
                             System.out.println("Дело " + spisokTask.get(nTaskForRedact - 1) + " помечено в списке как НЕВЫПОЛНЕННОЕ.");
                         } else {
                             System.out.println("Это дело УЖЕ отмечено в списке как НЕВЫЫПОЛНЕННОЕ");
                         }
                         break;
                     case 5:
                         break;
                     default:
                         System.out.println("Неккоректно введён номер.");
                         break;

                 }
             }
             else {
                 System.out.println("Мероприятий на эту дату не заплапнировано");
             }
         } catch (ParseException e) {
             System.out.println("Неккоректно введена дата!!!");
         }
    }
}
>>>>>>> 1900b1d3fd7904182b106713e6023eaa98f2f4da
