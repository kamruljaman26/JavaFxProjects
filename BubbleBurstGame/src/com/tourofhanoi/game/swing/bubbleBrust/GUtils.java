package com.tourofhanoi.game.swing.bubbleBrust;

import java.awt.*;
import java.util.Random;

public class GUtils {
    private static final Color[] colors = {
            Color.blue, Color.cyan, Color.green,
            Color.magenta, Color.orange, Color.pink,
            Color.red, Color.yellow
    };

    public static Color getRandomColor(){
        return colors[getRandomNum(0,8)];
    }

    public static int getRandomNum(int min, int max){
        Random r = new Random();
        int result = r.nextInt(max-min) + min;
        return result;
    }
}
