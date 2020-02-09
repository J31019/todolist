package com.company;

public class MyExeption2 extends Exception {

    public String getMessage () {
        return "Дата введенная Вами уже прошла!\nВведите дату начиная с сегодня!";
    }
    public String getMessage2 () {
        return "На данную дату ничего не записанно!";
    }
}
