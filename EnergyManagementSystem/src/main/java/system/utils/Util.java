package system.utils;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Util {

    // set image from path
    public static boolean setImage(ImageView view, String filepath) {
        try {
            FileInputStream input = new FileInputStream(filepath);
            Image image = new Image(input);
            view.setImage(image);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Image no found!");
            e.printStackTrace();
            return false;
        }
    }
}
