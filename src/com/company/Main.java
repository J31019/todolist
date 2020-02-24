package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Todolist tl = new Todolist();

        tl.createFile();
	while (true) {
        System.out.println("\nВыберите действие:\n1) Добавить задание\n2) Выбрать задание для редактирования\n3) Показать список дел\n4) Завершить работу");
        switch (tl.getNumber()) {
            case 1:
                tl.add();
                try {
                    tl.writeFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                tl.selectTask();
                try {
                    tl.writeFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                tl.menu3();
                break;
            case 4:
                System.out.println("Завершить работу");
                return;
            default:
                System.err.println("Не правильно сделан выбор.\nВведите № из указанного списка.");
        }
    }
  }

 }

