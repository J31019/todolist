package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Task {

    private String description;

    private Date date;

    private Status status;

    public Task(String description, Date date) {
        this.description = description;
        this.date = date;
        this.status = Status.TODO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


