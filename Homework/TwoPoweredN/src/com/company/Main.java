package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer,Integer> al = new HashMap<>();
        int i = 2;
        int n = 1000;
        al.put(1,i);
        al.put(2,0);
        for (int d = 1;d<n;d++) {
            for (int index = 1; index < al.size(); index++) {
                al.put(index, al.get(index) * 2);
            }
            for (int dec = 1; dec < al.size(); dec++) {
                    if (al.get(dec) >= 10) {
                        al.put(dec, al.get(dec) - 10);
                        if (al.get(dec + 1) == 0 && dec+1 == al.size()) {
                            al.put(dec + 1, 1);
                            al.put(al.size()+1, 0);
                            break;
                        } else {
                            al.put(dec + 1, al.get(dec + 1) + 1);
                        }
                    }
                }
            }
        for (int s = al.size()-1; s>0;s--){
            System.out.print(al.get(s));
        }
    }
}
