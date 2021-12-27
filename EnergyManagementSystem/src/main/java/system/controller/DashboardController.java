package system.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.model.Stageable;
import system.utils.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DashboardController implements Stageable {

    private Stage stage; // main stage

    @FXML
    private ImageView logoImg; // title logo

    /**
     * initialize at start
     */
    @FXML
    public void initialize() {
        Util.setImage(logoImg, "images/system-logo.png");
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
