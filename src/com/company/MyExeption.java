package com.company;

public class MyExeption extends Exception {
    /*public MyExeption (){
        super();
    0}*/
    public String getMessage () {

        return "Не верно введена дата!\nВведите дату в формате \"DD/MM/YYYY\"";
    }

}
