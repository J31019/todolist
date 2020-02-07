package com.company;

import java.io.InvalidObjectException;

public class Main {

    public static void main(String[] args) {
        SerDeser serDeser = new SerDeser();
        AddingData data = new AddingData();
        try {
            data.dateTaskStatus.putAll(serDeser.deser());
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        data.menu();
        serDeser.ser(data);
    }
}