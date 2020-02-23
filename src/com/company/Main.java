package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Relis reliss = new Relis();
        reliss.readMapp();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Главное меню");
            System.out.println("1. Добавить задание");
            System.out.println("2. Выбрать задание");
            System.out.println("3. Показать список дел");
            System.out.println("4. Завершить работу");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    try {
                        reliss.addTasks();
                    } catch (MyExeption myExeption) {
                        System.err.println(myExeption.getMessage());
                    }catch (MyExeption2 myExeption2) {
                        System.err.println((myExeption2.getMessage()));
                    }
                    break;
                case 2:
                    try {
                        reliss.chooseTask();
                    } catch (MyExeption2 myExeption2) {
                        System.err.println(myExeption2.getMessage2());
                    } catch (MyExeption myExeption) {
                        System.err.println(myExeption.getMessage());
                    }
                    catch (MyExeption3 myExeption3) {
                        System.err.println(myExeption3.getMessage());
                    }
                    break;
                case 3:
                    try {
                        reliss.getListTask();
                    }
                    catch (MyExeption myExeption) {
                        System.err.println(myExeption.getMessage());
                    }
                    catch (MyExeption2 myExeption2) {
                        System.err.println(myExeption2.getMessage2());
                    }
                    break;
                case 4:
                    reliss.saveMapp();
                    System.out.println("Хорошего Вам дня!");
                    return;
                default:
                    System.err.println("Неверно выбран пункт меню!");
            }

        }
    }
}
