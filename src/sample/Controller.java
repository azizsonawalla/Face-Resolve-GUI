package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Stage stage;

    private String inputDirectory = "No input directory selected";
    private String outputDirectory = "No output directory selected";

    // UI Elements
    @FXML private Label inputDirectoryLabel;
    @FXML private Label outputDirectoryLabel;
    @FXML private Button inputDirectoryChooser;
    @FXML private Button outputDirectoryChooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputDirectoryLabel.setText(this.inputDirectory);
        outputDirectoryLabel.setText(this.outputDirectory);
    }

    @FXML
    private void openDirectoryChooser(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if(selectedDirectory != null){
            if (event.getSource().equals(inputDirectoryChooser)) {
                this.inputDirectory = selectedDirectory.getAbsolutePath();
                inputDirectoryLabel.setText(this.inputDirectory); // check for at least 1 image
            }
            if (event.getSource().equals(outputDirectoryChooser)) {
                this.outputDirectory = selectedDirectory.getAbsolutePath();
                outputDirectoryLabel.setText(this.outputDirectory);
            }
        }
    }

    @FXML
    private void openFileChooser(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            System.out.print(selectedFile);
            // validations:
            // in input folder
            // is of apt format
        }
    }
}