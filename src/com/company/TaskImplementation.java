package com.company;

public enum TaskImplementation {
    FALSE("Невыполнено"),
    TRUE("Выполено");
    private String status;

    TaskImplementation(String status){
        this.status =status;
    };

    public String getStatus(){
        return this.status;
    }
}