package system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.App;
import system.model.adt.User;
import system.model.adt.SceneName;
import system.utils.Stageable;
import system.model.dao.CustomerDaoFile;
import system.model.dao.Dao;
import system.utils.PasswordManager;
import system.utils.StringData;
import system.utils.Utils;


public class LoginController implements Stageable {

    private Stage stage; // main stage

    @FXML
    private ImageView logoImg; // title logo

    @FXML
    private Label errorm; // showing error messages

    @FXML
    private TextField username; // entered username

    @FXML
    private PasswordField password; // entered password

    /**
     * initialize at start
     */
    @FXML
    public void initialize() {
        Utils.setImage(logoImg, "images/system-logo.png"); // set logo
    }

    // Customer DAO
    private final Dao<User> cusDao = new CustomerDaoFile();

    /**
     * Customer login
     */
    public void customerLogin(ActionEvent actionEvent) {
        User customer = cusDao.get(username.getText());
        // if customer already registered, username veryfied
        if (customer != null && !password.getText().equals("")) {
            // verify password
            boolean verified = PasswordManager.verify(password.getText(), customer.getPassword());
            if (verified) {
                // successful login
                System.out.println("Login Success, load dashboard");
                stage.setScene(App.getScenes().get(SceneName.DASHBOARD).getScene());
            } else {
                // invalid password
                errorm.setText(StringData.invalidPass);
            }

        } else {
            errorm.setText(StringData.invalidUserPass);
        }
    }

    /**
     * Customer sign-up
     */
    public void customerSignUp(ActionEvent actionEvent) {
        stage.setScene(App.getScenes().get(SceneName.SIGNUP).getScene()); // load signup page
    }

    /**
     * exit button handler
     */
    public void exitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}