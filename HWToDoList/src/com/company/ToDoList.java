package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToDoList implements ToDoListImpl {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static ArrayList<Task> mainList = new ArrayList<>();       //Коллекция, в которой будут храниться все задачи
    static Date todayDate = new Date();
    TaskEditor taskEditor = new TaskEditor();       //Редактор задач

    public void mainMenu() throws IOException, ParseException {
        System.out.println("Main menu:");
        System.out.printf("%d. Add new task\n%d. Choose the task\n" +
                "%d. Show to do list\n%d. Clear to do list\n%d. Complete the work\n", 1, 2, 3, 4, 5);
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1:
                addTask();
                taskEditor.saveInFile();
                break;
            case 2:
                editTask();
                break;
            case 3:
                showList();
                break;
            case 4:
                clearList();
                taskEditor.saveInFile();
                break;
            case 5:
                taskEditor.saveInFile();
                System.exit(0);
            default:
                System.out.println("Enter the number from 1 to 5\n");
        }

    }

    public void start() throws IOException, ParseException {
        File save = new File("SaveList.txt");
        if (save.exists()) {
            FileReader reader = new FileReader("SaveList.txt");
            int c = reader.read();
            if (c != -1) {
                loadFromFile();
            }
        } else {
            save.createNewFile();
        }
    }

    public void addTask() throws ParseException, IOException {
        System.out.println("Enter the date in the format day/month/year");
        String userDate = reader.readLine();    //Принимаем пользовательскую дату
        String today = dateFormat.format(todayDate);
        if (dateFormat.parse(userDate).before(dateFormat.parse(today))) {
            System.out.println("You cannot add a task with a past date\n");
            return;
        }
        String description = "";
        do {
            System.out.println("Enter the description of your task");
            description = reader.readLine();
            if (description.equals("")) {
                System.out.println("You cannot add a task without description\n");
            }
        } while (description.equals(""));       //цикл не позволяет создать задачу без описания

        for (Task task : mainList) {     //Проверяем наличие ранее добавленных задач с таким же описанием
            if (task.getDate().equals(dateFormat.parse(userDate))) {
                if (task.getDescription().equals(description)) {
                    System.out.println("The task already exists\n");
                    return;
                }
            }
        }
        Task task = new Task(description, dateFormat.parse(userDate));
        mainList.add(task);
    }

    public void editTask() throws ParseException, IOException, IndexOutOfBoundsException {
        ArrayList<Task> editList = new ArrayList<>();   //Коллекция хранит задачи для редактирования на выбранную пользователем дату.
        ArrayList<Integer> indexes = new ArrayList<>();   //Коллекция хранит индексы элементов в коллекции MAIN_LIST, которые пользователь выбрал для редактирования
        System.out.println("Enter the date in the format day/month/year");
        Date userDate = dateFormat.parse(reader.readLine());       //Принимаем пользовательскую дату
        for (Task task : mainList) {     //При помощи цикла ищем задачи на дату, введенную пользователем и добавляем в колекцию для редактирования, а ее индекс в коллекцию индексов
            if (task.getDate().equals(userDate)) {
                editList.add(task);
                indexes.add(mainList.indexOf(task));
            }
        }
        if (taskEditor.showEditList(editList, indexes) != -1) {
            taskEditor.showMenu(indexes, editList);
        }
    }

    public void showList() throws ParseException, IOException {
        if (mainList.isEmpty()) {
            System.out.println("The to do list is empty\n");
            return;
        }
        TaskPrinter printer = new TaskPrinter();
        while (true) {
            System.out.printf("%d. For today\n%d. For week\n%d. For the specified date\n%d. Show full to do list\n" +
                    "%d. Back\n", 1, 2, 3, 4, 5);
            int changeAction = Integer.parseInt(reader.readLine());
            switch (changeAction) {
                case 1:
                    printer.printTodayTasks(mainList);
                    break;
                case 2:
                    printer.printWeekTasks(mainList);
                    break;
                case 3:
                    System.out.println("Enter the date in the format day/month/year");
                    Date userDate = dateFormat.parse(reader.readLine());
                    printer.printSpecificTasks(mainList, userDate);
                    break;
                case 4:
                    printer.printList(mainList);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter the number from 1 to 5 \n");
            }
        }
    }

    public void clearList() throws IOException {
        System.out.println("Are you sure? (y/n)");
        String answer = reader.readLine();
        if (answer.equals("y") || answer.equals("Y")) {
            mainList.clear();
            System.out.println("The to do list has been cleared\n");
        }

    }

    public void loadFromFile() throws IOException, ParseException {
        FileReader fileReader = new FileReader("SaveList.txt");
        String fromSaveList = "";     //переменная для сохранения содержимого из сэйв-файла
        int c = fileReader.read(); //возвращает значение -1 когда метод завершается, т.е. файл прочитан
        while (c != -1) {        //чтение содержимого файла и запись в переменную fromSaveList
            fromSaveList = fromSaveList + (char) c;
            c = fileReader.read();
        }
        fileReader.close();

        String[] chunks = fromSaveList.split(String.valueOf((char) 174));       //массив содержит строки с параметрами задач, полученные из файла fromSaveList
        for (int i = 0; i < chunks.length; i += 3) {       //передача строк из массива chunks в конструктор Task и добавление полученных задач в основную коллекцию
            Task task = new Task(dateFormat.parse(chunks[i]), chunks[i + 1], chunks[i + 2]);
            mainList.add(task);
        }
        for (Task task : mainList) {      //Проверка на наличие просроченных задач
            String status = task.getStatus();
            String taskDate = dateFormat.format(task.getDate());
            String today = dateFormat.format(todayDate);
            if (status.equals("UNCOMPLETED") && dateFormat.parse(taskDate).before(dateFormat.parse(today))) {
                task.setStatus(Status.EXPIRED);
            }
        }

    }

}
