package system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import system.model.Customer;
import system.model.CustomerDaoFile;
import system.model.Dao;
import system.utils.PasswordManager;
import system.utils.StringData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class LoginController {


    @FXML
    private ImageView logoImg;

    @FXML
    private Label errorm;

    @FXML
    private TextField username; // entered username

    @FXML
    private PasswordField password; // entered password

    /**
     * initialize at start
     */
    @FXML
    public void initialize() {
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

    // Customer DAO
    private Dao<Customer> cusDao = new CustomerDaoFile();

    /**
     * Customer login
     *
     * @param actionEvent
     */
    public void customerLogin(ActionEvent actionEvent) {
        Customer customer = cusDao.get(username.getText());
        // if customer already registered, username veryfied
        if (customer != null && !password.getText().equals("")) {
            // verify password
            System.out.println(customer.getPassword());
            System.out.println(password.getText());
            boolean verified = PasswordManager.verify(password.getText(), customer.getPassword());
            if(verified){
                // successful login
                System.out.println("Login Sucess");
            }else {
                // invalid password
                errorm.setText(StringData.invalidPass);
            }

        } else {
            errorm.setText(StringData.invalidUserPass);
        }
    }

    /**
     * Customer sign-up
     *
     * @param actionEvent
     */
    public void customerSignUp(ActionEvent actionEvent) {
        // todo: signup page
    }
}