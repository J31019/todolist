package com.company;

import java.io.IOException;
import java.text.ParseException;

public interface ToDoListImpl {
    void addTask() throws ParseException, IOException;
    void editTask() throws ParseException, IOException;
    void showList() throws ParseException, IOException;
    void loadFromFile() throws IOException, ParseException;
    void start() throws IOException, ParseException;
}
