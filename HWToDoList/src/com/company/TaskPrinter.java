package com.company;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static com.company.ToDoList.dateFormat;
import static com.company.ToDoList.todayDate;

public class TaskPrinter {

    public void printList(ArrayList<Task> mainList) {
        for (Task task : mainList) {
            printTask(task);
        }
    }

    public void printTask(Task task) {
        System.out.println(dateFormat.format(task.getDate()));
        System.out.print(task.getDescription());
        System.out.print("\nStatus - ");
        System.out.println(task.getStatus());
        System.out.println("");
    }

    public void printTodayTasks(ArrayList<Task> mainList) {
        ArrayList<Task> todayList = new ArrayList<>();
        for (Task task : mainList) {
            if (dateFormat.format(task.getDate()).equals(dateFormat.format(todayDate))) {
                todayList.add(task);
            }
        }
        if (todayList.isEmpty()) {
            System.out.println("There are no tasks for today\n");
        }else {
            printList(todayList);
        }

    }

    public void printWeekTasks(ArrayList<Task> mainList) {
        ArrayList<Task> weekList = new ArrayList<>();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date plusWeek = calendar.getTime();
        for (Task task : mainList) {
            if (dateFormat.format(task.getDate()).equals(dateFormat.format(todayDate))) {       // выводим задачи на сегодня
                weekList.add(task);
            }
            if (task.getDate().before(plusWeek) && task.getDate().after(todayDate)) {       //выводим задачи на неделю вперед
                weekList.add(task);
            }
        }
        if (weekList.isEmpty()) {
            System.out.println("There are no tasks for coming week\n");
        }
        else {
            printList(weekList);
        }
    }

    public void printSpecificTasks(ArrayList<Task> mainList, Date userDate) {
        ArrayList<Task> onDateList = new ArrayList<>();
        for (Task task : mainList) {     //При помощи цикла ищем задачи на дату, введенную пользователем
            if (task.getDate().equals(userDate)) {
                onDateList.add(task);
            }
        }
        if (onDateList.isEmpty()) {
            System.out.println("There are no tasks for this date\n");
        }else {
            printList(onDateList);
        }
    }
}
