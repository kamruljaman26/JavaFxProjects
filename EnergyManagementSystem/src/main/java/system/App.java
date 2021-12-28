package system;

import javafx.application.Application;
import javafx.stage.Stage;
import system.model.adt.SceneName;
import system.utils.Fxml;
import system.utils.StringData;

import java.util.HashMap;
import java.util.Map;

/**
 * Sets all scene info into a Map and displays the main scene.
 * <p>
 * Note: This class should be launched with these VM flags:
 * <p>
 * {@code --module-path /path/to/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml}
 *
 * @author Kamrul Jaman
 */
public class App extends Application {

    // Holds the information for various scenes to switch between
    private static final Map<SceneName, Fxml> scenes = new HashMap<>();

    // All file path of fxml view
    private static final String LOGIN_FXML = "/fxml/login-view.fxml";
    private static final String SIGNUP_FXML = "/fxml/signup-view.fxml";
    private static final String DASHBOARD_FXML = "/fxml/dashboard-view.fxml";

    @Override
    public void start(Stage stage) {
        // register all scene
        scenes.put(SceneName.LOGIN, new Fxml(LOGIN_FXML, SceneName.LOGIN, stage)); // login
        scenes.put(SceneName.SIGNUP, new Fxml(SIGNUP_FXML, SceneName.SIGNUP, stage)); // signup
        scenes.put(SceneName.DASHBOARD, new Fxml(DASHBOARD_FXML, SceneName.DASHBOARD, stage)); // dashboard

        // getScene() will load the FXML file the first time
        stage.setScene(scenes.get(SceneName.LOGIN).getScene());
        stage.setTitle(StringData.title);
        stage.show();
    }

    // @return a Map of the {@link Fxml} by {@link SceneName}
    public static Map<SceneName, Fxml> getScenes() {
        return scenes;
    }

    // Update the scene Map with new FxmlInfo
    public static synchronized void updateScenes(SceneName name, Fxml info) {
        scenes.put(name, info);
    }

    // main
    public static void main(String[] args) {
        launch(args);
    }
}
