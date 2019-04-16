package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import static main.AppConfigConstants.*;

public class Main extends Application {

    private String FXML_FILE = "SceneOne.fxml";

    private Stage primaryStage;
    private Map<Integer,Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_FILE));
        loader.setController(new SceneOneController());
        Pane root = loader.load();
        primaryStage.setTitle(APP_NAME);
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showScene(int sceneNumber) {
        primaryStage.setScene(scenes.get(sceneNumber));
        primaryStage.show();
    }

    private Scene createSceneOne() {
        return null;
        // TODO!
    }

    public static void main(String[] args) {
        launch(args);
    }
}
