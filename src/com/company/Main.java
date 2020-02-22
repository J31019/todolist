package com.company;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {

          try {
            MyMenu.getMyMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
          System.out.println("Проверьте ввод даты");
      }
    }
}