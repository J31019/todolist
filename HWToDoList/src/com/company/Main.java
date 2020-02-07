package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ToDoList toDoList = new ToDoList();
        System.out.println("\nWelcome to the \"To do list\" application!\n");
        try {
            toDoList.start();
        } catch (IOException | ParseException e) {
            System.out.println("Error of reading from file");
        }
        while (true) {
            try {
                toDoList.mainMenu();
            } catch (NumberFormatException e) {
                System.out.println("ERROR! The entered number is incorrect. Try again");
            } catch (ParseException e) {
                System.out.println("The entered date is incorrect. Try again");
            } catch (IOException e) {
                System.out.println("Error of writing in file");
            }

        }
    }
}

