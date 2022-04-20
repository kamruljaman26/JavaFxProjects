package system.utils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.App;
import system.SceneName;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Code Copied from GitHub (original @author Knute Snortum)
 *
 * The scenes are loaded lazily, that is, only the first time they are called.
 * After that, the loaded scene is looked and returned.
 *
 * @author Knute Snortum
 */
public class Fxml {

    private String resourceName;
    private SceneName sceneName;
    private Stage stage;
    private Scene scene;

    public Fxml(String resourceName, SceneName sceneName, Stage stage) {
        this.resourceName = resourceName;
        this.sceneName = sceneName;
        this.stage = stage;
    }

    public String getResourceName() {
        return resourceName;
    }

    public SceneName getSceneName() {
        return sceneName;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        if (scene == null) {
            scene = load(this);
        }
        return scene;
    }

    /**
     * @return {@code true} if the scene has been built, otherwise {@code false}
     */
    public boolean hasScene() {
        return scene != null;
    }

    /**
     * @return the primary stage for this Scene
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Either builds the scene from {@link Fxml} or loads the built scene.<br>
     * <br>
     * Uses this class's ClassLoader to find the URL of the FXML file.  If the URL
     * is {@code null} then the FXML file could not be found.
     *
     * @param fxml the FXML file info to load the scene with
     * @return the built scene, or {@code null} if there was an error
     */
    public Scene load(Fxml fxml) {

        if (fxml.hasScene()) {
            return fxml.getScene();
        }

        URL url = getClass().getResource(fxml.getResourceName());

        if (url == null) {
            System.out.printf("The URL for the resource %s was not found", fxml.getResourceName());
            debugInfo(fxml.getResourceName()); // not required
            Platform.exit();
            return null;
        }

        FXMLLoader loader = new FXMLLoader(url);
        Scene scene;

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
            return null;
        }

        // Write back the updated FxmlInfo to the scenes Map in Main
        fxml.setScene(scene);
        App.updateScenes(fxml.getSceneName(), fxml);

        Stageable controller = loader.getController();
        if (controller != null) {
            controller.setStage(fxml.getStage());
        }

        return scene;
    }

    // This method isn't required, but it can help figure out why an FXML file isn't loading
    private void debugInfo(String resourceName) {
        System.out.printf("Working Directory = %s\n", System.getProperty("user.dir"));
        System.out.printf("Resources for %s\n", resourceName);
        try {
            Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(resourceName);
            while (urls.hasMoreElements()) {
                System.out.println(urls.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
