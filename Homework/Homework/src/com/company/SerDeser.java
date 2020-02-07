package com.company;

import java.io.*;
import java.util.HashMap;

public class SerDeser {
    public void ser(AddingData addingData){
        File file = new File("D:/tasks.data");
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if(fos!=null){
                oos = new ObjectOutputStream(fos);
                oos.writeObject(addingData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos!=null){
                oos.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public HashMap deser() throws InvalidObjectException {
        File file = new File("D:/tasks.data");
        ObjectInputStream ois = null;
        AddingData addingData = new AddingData();
        try {
            FileInputStream fis = new FileInputStream(file);
            if(fis!=null){
                ois = new ObjectInputStream(fis);
                addingData = (AddingData) ois.readObject();
                return addingData.dateTaskStatus;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new InvalidObjectException("что то пошло не так...");
    }
}
