package system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.App;
import system.model.adt.Customer;
import system.model.adt.SceneName;
import system.utils.Stageable;
import system.model.dao.CustomerDaoFile;
import system.model.dao.Dao;
import system.utils.PasswordManager;
import system.utils.StringData;
import system.utils.Utils;

import java.security.NoSuchAlgorithmException;


public class SignupController implements Stageable {

    private Stage stage; // main stage
    @FXML
    private ImageView logoImg; // title logo
    @FXML
    private TextField nameFiled; // name
    @FXML
    private TextField phoneNumFiled; // name
    @FXML
    private TextField addressFiled; // address
    @FXML
    private TextField usernameFiled; // username
    @FXML
    private TextField passwordFiled; // password
    @FXML
    private TextField confirmPasswordFiled; // confirm password
    @FXML
    private Label errorm; // showing error messages

    /**
     * initialize at start
     */
    @FXML
    public void initialize() {
        // set logo image
        Utils.setImage(logoImg, "images/system-logo.png");
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * signup button handler, this method will verify all inform to create a new customer in system
     *
     * @param actionEvent button action
     */
    public void signUpButtonAction(ActionEvent actionEvent) {
        // create customer & handle error
        String name = nameFiled.getText(), address = addressFiled.getText(),
                username = usernameFiled.getText(), phone = phoneNumFiled.getText(),
                password = passwordFiled.getText(), confirmPass = confirmPasswordFiled.getText();

        // if any filed not filled
        if (name.equals("") || phone.equals("") || address.equals("") ||
                username.equals("") || password.equals("") || confirmPass.equals("")) {
            errorm.setText(StringData.fillAllInfo);
            return;
        }

        // check is the username is available
        Dao<Customer> dao = new CustomerDaoFile();
        if (dao.get(username) == null) {
            //check password matching
            if (password.equals(confirmPass)) {
                // create & store customer
                try {
                    String hashPass = PasswordManager.encode(password);
                    Customer customer = new Customer(name, phone, address, username, hashPass);
                    boolean saved = dao.save(customer);

                    // todo navigate to energy option
                    if (saved) System.out.println("Customer added");
                } catch (NoSuchAlgorithmException e) {
                    errorm.setText(StringData.errorCreatingCustomer);
                }
            } else {
                errorm.setText(StringData.passwordNotMatched);
            }
        } else {
            errorm.setText(StringData.usernameNotAvailable);
        }

    }

    /**
     * back button handler
     *
     * @param actionEvent button action
     */
    public void backButtonAction(ActionEvent actionEvent) {
        stage.setScene(App.getScenes().get(SceneName.LOGIN).getScene());
    }

    /**
     * exit button handler
     *
     * @param actionEvent button action
     */
    public void exitButtonAction(ActionEvent actionEvent) {
        // terminate the app
        System.exit(0);
    }
}
