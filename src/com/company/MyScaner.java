package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyScaner {


    public static Date getScaner() {
        Date date = null;
        String symbol;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите дату в следующем формате \"ДД/ММ/ГГГГ\": ");
            symbol = scanner.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                date = sdf.parse(symbol);
                return date;
            } catch (ParseException e) {
                System.out.println("Проверьте введенную дату.");
            }
        }
    }


    public static int getScanerInt() {

        while (true) {
            char[] symbol = null;
            String date = "";
            Scanner scanner = new Scanner(System.in);
            symbol = scanner.nextLine().toCharArray();

            for (int i = 0; i < symbol.length; i++) {

                if (symbol[i] >= '0' && symbol[i] <= '9') {
                    date += symbol[i];
                    if (i == symbol.length - 1) {
                        return Integer.parseInt(date);
                    }
                } else {
                    System.err.println("Ошибка при вводе номера позиции,\n номер позиции целое число.\nПовторите ввод позиции.");
                    break;
                }
            }
        }
    }
}
