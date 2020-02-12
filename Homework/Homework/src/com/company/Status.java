package com.company;

public enum Status {
    ISDONE("Выполнено"),
    ISNOTDONE("Невыполнено");

    private String status;

    Status(String status){
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
