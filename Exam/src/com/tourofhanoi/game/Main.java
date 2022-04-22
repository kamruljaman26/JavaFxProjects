package com.tourofhanoi.game;

import java.util.ArrayList;
import java.util.Date;

public class Main {
        private boolean a;

    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date(349324);
        Date d3 = d1;
        System.out.println("(1)"+(d1==d2));
        System.out.println("(2)"+(d1==d2));
        System.out.println("(2)"+(d1.equals(d2)));
        System.out.println("(2)"+(d1.equals(d3)));

        ArrayList<String> list = new ArrayList<>();
//        return list.isEmpty(); // 1.
//        return list.size(); // 2
//        return list.get(0); // 3
//        list.add(o);//4
    }
}
