package system.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SignupController {

    @FXML
    private ImageView logoImg; // title logo

    /**
     * initialize at start
     */
    @FXML public void initialize() {
        // set logo image
        try {
            FileInputStream input = new FileInputStream("images/system-logo.png");
            Image image = new Image(input);
            logoImg.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println("Logo Image no found!");
            e.printStackTrace();
        }
    }
}
