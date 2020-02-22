package com.company;

import java.util.Calendar;
import java.util.Scanner;

public class MyScaner {
    static Calendar calendar = Calendar.getInstance();

    public static String getScaner() {
        while (true) {
            char[] symbol = null;
            String date = "";
            String year = "";
            String month = "";
            String day = "";

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите дату в следующем формате \"ДД/ММ/ГГГГ\": ");
            symbol = scanner.nextLine().toCharArray();

            if ((symbol.length - 1) == 9) {

                for (int i = 0; i < 10; i++) {

                    if ((i == 0 || i == 1 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8 || i == 9)) {

                        if (0 <= i && i < 2) {
                            day += symbol[i];
                        }

                        if (3 <= i && i < 5) {
                            month += symbol[i];
                        }

                        if (6 <= i && i < 10) {
                            year += symbol[i];
                        }

                        if (symbol[i] >= '0' && symbol[i] <= '9') {
                           if (i == 9) {

                                if (0 <= Integer.parseInt(month) && Integer.parseInt(month) <= 12) {
                                    calendar.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
                                    if (0 < Integer.parseInt(day) && Integer.parseInt(day) <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                                        date = day + '/' + month + '/' + year;
                                    } else {
                                        System.out.println("Неверно указан день.");
                                        break;
                                    }
                                } else {
                                    System.out.println("Неверно указан месяц.");
                                    break;
                                }
                               return date;
                            }
                        } else {
                            System.out.println("ошибка ввода: здесь должна присутствовать цифра");
                            break;
                        }
                    }
                    if (i == 2 || i == 5) {
                        if (symbol[i] != '/') {
                            System.out.println("ошибка ввода: здесь должна присутствовать \"/\":");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Неверный формат даты.\n Повторите попытку.");
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
