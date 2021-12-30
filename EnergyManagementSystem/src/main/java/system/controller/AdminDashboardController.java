package system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.model.adt.Customer;
import system.model.dao.CustomerDaoFile;
import system.model.dao.Dao;
import system.utils.Stageable;
import system.utils.Utils;

import java.util.ArrayList;

public class AdminDashboardController implements Stageable {

    private Stage stage; // main stage
    private Dao<Customer> dao = new CustomerDaoFile();

    @FXML
    private ImageView logoImg; // title logo

    @FXML
    private Label errorm; // title logo

    @FXML
    private TextField userNameSearchFiled; // username

    @FXML
    private TextField nameSearchFiled; // name

    @FXML
    private ListView<Customer> listView; // listview


    /**
     * initialize at start
     */
    @FXML
    public void initialize() {
        Utils.setImage(logoImg, "images/system-logo.png");
        // show all customer at beginning
        listView.setItems(FXCollections.observableList(dao.getAll()));
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Search on customer db and show all found result that contain the given name
     */
    public void nameSearchAction(ActionEvent actionEvent) {
        String name = nameSearchFiled.getText();
        FilteredList<Customer> filtered = FXCollections
                .observableList(dao.getAll())
                .filtered(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()));

        if (filtered.isEmpty()) {
            errorm.setText(name + " not found!");
            listView.setItems(null);
        } else {
            errorm.setText("");
            listView.setItems(filtered);
        }
    }

    public void usernameSearchAction(ActionEvent actionEvent) {
        String username = userNameSearchFiled.getText();
        Customer customer = dao.get(username);

        ObservableList<Customer> observableList = FXCollections.observableList(new ArrayList<>());
        observableList.add(customer);

        if (customer == null) {
            errorm.setText(username + " not found!");
            listView.setItems(null);
        } else {
            errorm.setText("");
            listView.setItems(observableList);
        }
    }

    public void exitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
