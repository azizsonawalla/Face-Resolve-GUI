package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private String FXML_FILE = "SceneOne.fxml";

    // Application Configuration
    private String APP_NAME = "Face Resolve";
    private int APP_WIDTH = 1000;
    private int APP_HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_FILE));
        loader.setController(new SceneOneController());
        Pane root = loader.load();
        primaryStage.setTitle(APP_NAME);
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
