package com.company;

import java.io.Serializable;

public class Filling implements Serializable {
        String task;
        String status;
        Filling(String task, String status){
            this.task = task;
            this.status = status;
    }
}