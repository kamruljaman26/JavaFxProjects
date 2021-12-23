package system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Application class, and this is the starting point of the full
 * management system.
 * @author kamrul
 */
public class MainApplication extends Application {

    /**
     * default init method of the application
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * Initial Start method from javaFx application
     * @param stage Stage is created internally by the application platform
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Energy Supplier Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The method will when app will stop
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     * Main method to run application
     * @param args cmd line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}