package com.company;

public enum Status {
    DONE("Выполнено"),
    NOTDONE("Невыполнено");

    private String value;

    Status(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
