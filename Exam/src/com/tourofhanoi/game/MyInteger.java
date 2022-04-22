package com.tourofhanoi.game;

public class MyInteger {

    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    public boolean isOdd() {
        return value % 2 != 0;
    }

    public static int parseInt(String value){
        return Integer.parseInt(value);
    }
}
