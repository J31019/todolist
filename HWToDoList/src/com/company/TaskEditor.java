package com.company;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static com.company.ToDoList.mainList;


public class TaskEditor {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private int changeTask;     //Переменная хранит номер задачи из списка на выбранную пользователем дату

    public int showEditList(ArrayList<Task> editList, ArrayList<Integer> indexes) throws IOException {
        if (editList.isEmpty()) {
            System.out.println("There are no any tasks on this day\n");
            return -1;
        } else {
            for (Task task : editList) {
                TaskPrinter printer = new TaskPrinter();
                System.out.print(editList.indexOf(task)+1);
                System.out.print(". ");
                printer.printTask(task);
            }

            while (true) {
                System.out.println("Enter the number of task to edit it");      //Если на эту дату есть задачи - просим пользователя выбрать какую редактировать
                changeTask = Integer.parseInt(reader.readLine()) - 1;
                if (changeTask > editList.size() - 1) {
                    System.out.println("Error. The entered index is incorrect. Try again");
                }else {
                    break;
                }
            }
        }
        return 0;
    }

    public void showMenu(ArrayList<Integer> indexes, ArrayList<Task> editList) throws IOException {
        while (true) {      //Вывод меню редактирования
            System.out.printf("%d. Change description\n%d. Delete\n%d. Mark as completed\n%d. Mark as uncompleted\n%d. Cancel\n", 1, 2, 3, 4, 5);
            int changeAction = Integer.parseInt(reader.readLine());
            //scanner.nextLine();
            switch (changeAction) {
                case 1:
                    changeDescription(editList);
                    saveInFile();
                    break;
                case 2:
                    deleteTask(indexes);
                    saveInFile();
                    return;
                case 3:
                    editList.get(changeTask).markCompleted();
                    saveInFile();
                    break;
                case 4:
                    editList.get(changeTask).markUnCompleted();
                    saveInFile();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter the correct number");
            }
        }
    }

    public void changeDescription(ArrayList<Task> editList) throws IOException {
        System.out.println("Enter the new description:");
        String newDescription = reader.readLine();
        if (newDescription.equals(editList.get(changeTask).getDescription())) {
            System.out.println("The new description must be different!\n");
        } else {
            editList.get(changeTask).setDescription(newDescription);
        }

    }

    public void deleteTask(ArrayList<Integer> indexes) throws IOException {
        System.out.println("Do you really want to delete this task? (y/n)");
        if (reader.readLine().equals("y") || reader.readLine().equals("Y")) {
            int index = indexes.get(changeTask);    //Получение индекса, удаляемого элемента из коллекции indexes
            mainList.remove(index);
            System.out.println("The task was removed successfully\n");
        }

    }
    public void saveInFile() throws IOException {
        FileWriter fileWriter = new FileWriter("SaveList.txt");
        for (Task task : mainList) {
            fileWriter.write(ToDoList.dateFormat.format(task.getDate()));
            fileWriter.write((char) 174);   //разделитель
            fileWriter.write(task.getDescription());
            fileWriter.write((char) 174);   //разделитель
            fileWriter.write(task.getStatus());
            fileWriter.write((char) 174);
        }
        fileWriter.close();
    }
}
