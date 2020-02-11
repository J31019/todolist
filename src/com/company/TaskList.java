package com.company;

import java.util.ArrayList;
import java.util.List;

public class TaskList{

    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
