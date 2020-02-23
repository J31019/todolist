package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DataCheck {

    Date d;

    public DataCheck(String dat) throws MyExeption2, MyExeption {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/y");

        try {
            d = df.parse(dat);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        System.out.println(d);
        String f = df.format(d);
        System.out.println(f);
        System.out.println(dat);
        Date nowDay = new Date();
        Long nowDayLong = nowDay.getTime() - 86400000;//получаем вчерашний день(в миллисик)
        Long dLong = d.getTime();//день сегодня(в миллисик)
        if (!f.equals(dat)) {//проверка ввода корректной даты в необходимом формате
            throw new MyExeption();
        }
        if (dLong <= nowDayLong) {//проверка введенной даты из прошлого
            throw new MyExeption2();
        }
    }

    public DataCheck(String data, HashMap map) throws MyExeption2, MyExeption {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = df.parse(data);
            System.out.println(d);
            String f = df.format(d);
            if (!map.containsKey(f)) {      //проверяем имеется ли введенная дата в коллекции
                throw new MyExeption2();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            }
    }

}
