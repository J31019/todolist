package com.company;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ListOfCases implements Serializable {
    private String date;
    private String task;
    private Status status;

    public ListOfCases() {

    }

    public ListOfCases(String date, String task, Status status) {
        this.date = date;
        this.task = task;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String toString() {

        return this.date + " " + this.task + " " + this.status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListOfCases that = (ListOfCases) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}


