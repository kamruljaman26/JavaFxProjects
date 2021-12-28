package system.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.utils.Stageable;
import system.utils.Utils;

public class DashboardController implements Stageable {

    private Stage stage; // main stage

    @FXML
    private ImageView logoImg; // title logo

    /**
     * initialize at start
     */
    @FXML
    public void initialize() {
        Utils.setImage(logoImg, "images/system-logo.png");
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
