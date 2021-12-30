package system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.App;
import system.model.adt.Admin;
import system.model.adt.Customer;
import system.model.adt.SceneName;
import system.model.dao.CustomerDaoFile;
import system.model.dao.Dao;
import system.utils.PasswordManager;
import system.utils.Stageable;
import system.utils.StringData;
import system.utils.Utils;

import java.security.NoSuchAlgorithmException;


public class AdminLoginController implements Stageable {

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
    private final Dao<Customer> cusDao = new CustomerDaoFile();

    /**
     * Customer login
     */
    public void customerLogin(ActionEvent actionEvent) {
        // letter on we can extend the admin functionality now for simplicity of the program we are using a
        // constant admin to text search by customer username or name
        Admin admin = null;
        try {
            admin = new Admin("admin","admin","admin",
                    "admin", PasswordManager.encode("admin"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Admin:login encode pass error");
        }
        // if customer already registered, username verified
        if (admin != null && username.getText().equals(admin.getUsername()) && !password.getText().equals("")) {
            // verify password
            boolean verified = PasswordManager.verify(password.getText(), admin.getPassword());
            if (verified) {
                // successful login
                System.out.println("Login Success, load admin dashboard");
                stage.setScene(App.getScenes().get(SceneName.ADMIN_DASHBOARD).getScene());
            } else {
                // invalid password
                errorm.setText(StringData.invalidPass);
            }

        } else {
            errorm.setText(StringData.invalidUserPass);
        }
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

    public void backButtonAction(ActionEvent actionEvent) {
        stage.setScene(App.getScenes().get(SceneName.LOGIN).getScene());
    }
}