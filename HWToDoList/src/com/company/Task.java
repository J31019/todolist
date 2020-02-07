package com.company;

import java.util.Date;

public class Task {
    private String description;
    private Date date;
    private Status status;

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(com.company.Status status) {
        this.status = status;
    }

    public Task(String description, Date date) {
        this.description = description;
        this.date = date;
        status = com.company.Status.UNCOMPLETED;
    }

    public Task(Date date, String description, String status) {
        this.description = description;
        this.date = date;
        switch (status) {
            case "COMPLETED":
                this.status = com.company.Status.COMPLETED;
                break;
            case "UNCOMPLETED":
                this.status = com.company.Status.UNCOMPLETED;
                break;
            case "EXPIRED":
                this.status = com.company.Status.EXPIRED;
                break;
        }
    }

    public void markCompleted() {
        this.status = com.company.Status.COMPLETED;
    }

    public void markUnCompleted() {
        this.status = com.company.Status.UNCOMPLETED;
    }

}

enum Status {
    COMPLETED,
    UNCOMPLETED,
    EXPIRED
}
