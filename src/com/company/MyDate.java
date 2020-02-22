package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyDate {

    /**
     * toDate(int numberDate) - Ввод с клавиатуры даты
     * numberDate -
     * 0. Выводим текщую дату
     * 1. Проверяем дату чтобы была не меньше текущей
     * 2. Выводим введенную дату
     */

    public static String toDate(int numberDate) throws ParseException {
        Date today = new Date();
        SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
        if (numberDate == 0) {
            return day.format(today);
        } else {
            while (true) {
                System.out.print("Введите дату: \r");
                Date date = MyScaner.getScaner();
                if (numberDate == 1) {
                    if (choosDate(date, today)) {
                        return day.format(date);
                    } else {
                        System.out.println("Значение введенной даты не может быть меньше текущей.");
                    }
                }
                if (numberDate == 2) {
                    return day.format(date);
                }
            }
        }
    }

    //Проверяем введенную дату, больше она или меньше текущей не учитывая время
    private static Boolean choosDate(Date date, Date today) throws ParseException {

        SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");

        Date a = day.parse(day.format(date));
        Date b = day.parse(day.format(today));
        if (a.compareTo(b) != -1) {
            //Если дата больше и равна текущей
            return true;
        }
        return false;
    }
}