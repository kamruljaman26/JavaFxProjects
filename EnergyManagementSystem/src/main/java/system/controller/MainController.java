package system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.utils.StringData;

public class MainController {

    @FXML
    private Label appTitle; // app title

    @FXML
    private TextField username; // entered username

    @FXML
    private PasswordField password; // entered password

    /**
     * initialize at start
     */
    @FXML public void initialize() {
        // set title
        appTitle.setText(StringData.welcome);
    }

    /**
     * Customer login
     * @param actionEvent
     */
    public void customerLogin(ActionEvent actionEvent) {
        System.out.println(username.getText());
        System.out.println(password.getText());
    }

    /**
     * Customer sign-up
     * @param actionEvent
     */
    public void customerSignUp(ActionEvent actionEvent) {
        // todo: signup page
    }
}