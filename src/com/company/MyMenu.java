package com.company;

import java.io.IOException;
import java.text.ParseException;



public class MyMenu {

    public static void getMyMenu() throws IOException, ParseException {
        boolean bool = true;

        while (bool) {
            System.out.println("\n==================================");
            System.out.println("*****Меню:******");
            System.out.println("1. Добавить задание.");
            System.out.println("2. Выбрать задание.");
            System.out.println("3. Показать список дел.");
            System.out.println("4. Завершить работу.");
            System.out.println("==================================\n");
            System.out.print("Сделайте выбор: \r");

            switch (MyScaner.getScanerInt()) {
                case 1: {
                    MyChoos.List();
                    break;
                }
                case 2: {
                    getMenuChange();
                    break;
                }
                case 3: {
                    getMenuShow();
                    break;
                }
                case 4: {
                    bool = false;
                    System.out.println("Работа приложения завершена.");
                    break;
                }
                default: {
                    System.out.println("Не правильный выбор.\nПовторите попытку выбора.");
                }
            }
        }
    }

    private static void getMenuChange() throws ParseException, IOException {
        boolean bool = true;
        while (bool) {
            System.out.println("\n==================================");
            System.out.println("1. Изменить описание.");
            System.out.println("2. Удалить.");
            System.out.println("3. Пометить как выполненное.");
            System.out.println("4. Пометить как не выполнено.");
            System.out.println("5. Отмена.");
            System.out.println("==================================\n");
            System.out.print("Сделайте выбор: \r");

            switch (MyScaner.getScanerInt()) {
                case 1: {
                    MyChoos.getСhangeFix(MyDate.toDate(2));
                    break;
                }
                case 2: {
                    MyChoos.getСhangeRemove(MyDate.toDate(2));
                    break;
                }
                case 3: {
                    MyChoos.getСhangeTrue(MyDate.toDate(2));
                    break;
                }
                case 4: {
                    MyChoos.getСhangeFalse(MyDate.toDate(2));
                    break;
                }
                case 5: {
                    bool = false;
                    break;
                }
                default: {
                    System.out.println("Не правильный выбор.\nПовторите попытку выбора.");
                }
            }
        }
    }

    private static void getMenuShow() throws IOException, ParseException {
        boolean bool = true;
        while (bool) {
            System.out.println("\n==================================");
            System.out.println("1. На сегодня.");
            System.out.println("2. На эту неделю.");
            System.out.println("3. На выбранную дату.");
            System.out.println("4. Весь список.");
            System.out.println("5. Назад.");
            System.out.println("==================================\n");
            System.out.print("Сделайте выбор: \r");

            switch (MyScaner.getScanerInt()) {
                case 1: {
                    MyChoos.showTaskToDay(MyDate.toDate(0));
                    break;
                }
                case 2: {
                    MyChoos.showTaskWeek();
                    break;
                }
                case 3: {
                    MyChoos.showTaskToDay(MyDate.toDate(2));
                    break;
                }
                case 4: {
                    MyChoos.showTaskAll();
                    break;
                }
                case 5: {
                    bool = false;
                    break;
                }
                default: {
                    System.out.println("Не правильный выбор.\nПовторите попытку выбора.");
                }
            }
        }
    }
}
