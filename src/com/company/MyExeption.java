package com.company;

public class MyExeption extends Exception {
    public String getMessage () {
        return "Не верно введена дата!\nВведите дату в формате \"DD/MM/YYYY\"";
    }
}
