package com.company;

import java.io.*;
import java.util.*;

public class FileParser extends Main {
    public static HashMap<Date,ArrayList<Task>> parseLine (){
        HashMap<Date,ArrayList<Task>> main=new HashMap<Date,ArrayList<Task>>();
        //ArrayList<String []> pLine=new ArrayList<>();
        String[] text = new String[3];
        Condition condition = Condition.NOTDONE;
        try {
            String str = null;
            BufferedReader br = new BufferedReader(new FileReader("TODO.txt"));
            while ((str = br.readLine()) != null) {
                text = str.split("~");
                switch (text[2]){
                    case "NOTEDONE":
                        condition=Condition.NOTDONE;
                        break;
                    case "DONE":
                        condition=Condition.DONE;
                        break;
                    default:
                        System.out.println("Неизвестное состояние задачи");
                }
                if (main.containsKey(stringToDate(text[0]))){//проверка на наличие необходимой даты
                    ArrayList<Task> temp= main.get(stringToDate(text[0]));
                    temp.add(new Task(text[1],condition));
                    main.put(stringToDate(text[0]),temp);

                }else {
                    ArrayList<Task> newTask= new ArrayList<>();
                    newTask.add(new Task(text[1], condition));
                    main.put(stringToDate(text[0]), newTask);
                }
            }
            br.close();
        } catch (IOException exc) {
            System.out.println("IO error!" + exc);
        }
        return main;
    }
    public static void fileWrite(HashMap<Date,ArrayList<Task>> fW){
        FileWriter writer = null;
        try {
            writer = new FileWriter("TODO.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            //writer.append(' ');
            //writer.flush();
            //writer.close();
        } catch (IOException e) {
            System.err.println("Error"+e);
        }catch (NullPointerException n){
            System.err.println("Список пуст"+n);
        }*/

        String strTask=null;
        if (fW.isEmpty()){
             strTask=null;
            System.out.println("Список заданий пуст");
        }
        Set<Date> set = fW.keySet();
        Iterator<Date> iter = set.iterator();
        while (iter.hasNext()){
            Date date = iter.next();
            ArrayList<Task> arrTemp= fW.get(date);
            //System.out.println(dateToString(date));
            for (int i =0;i<arrTemp.size();i++){
                Task taskTemp=arrTemp.get(i);
                strTask=dateToString(date)+"~"+taskTemp.content+"~"+taskTemp.condition;
                //System.out.println(strTask);
            }
            try {
                try {
                    char[] c = strTask.toCharArray();
                    for (int i =0;i<c.length;i++){
                        writer.append(c[i]);
                    }
                    writer.write(System.getProperty("line.separator"));

                }catch (NullPointerException n){
                    System.err.println("В строке нет символов" + n);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Не удалось записать в файл");
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
