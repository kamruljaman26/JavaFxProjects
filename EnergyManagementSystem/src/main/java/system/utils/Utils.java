package system.utils;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Utils {
    // set image from path
    public static void setImage(ImageView view, String filepath) {
        try {
            FileInputStream input = new FileInputStream(filepath);
            Image image = new Image(input);
            view.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println("Image no found!");
            e.printStackTrace();
        }
    }
}
