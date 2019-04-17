package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.commons.io.FilenameUtils;
import static main.AppConfigConstants.*;

// TODO list:
// - Move colours to constants
// - Add functionality to 'Next' button
// - Add styling to scene

public class SceneOneController implements Initializable {

    // User configuration
    private String inputDirectory = "No input directory selected";
    private String outputDirectory = "No output directory selected";
    private ArrayList<String> inputImagePaths;

    // UI Elements
    @FXML private Label inputDirectoryLabel;
    @FXML private Label outputDirectoryLabel;
    @FXML private Button inputDirectoryChooser;
    @FXML private Button outputDirectoryChooser;
    @FXML private CheckBox autoChoosePreviewImage;
    @FXML private Button previewImageChooser;
    @FXML private Label inputDirectoryValidationStatus;
    @FXML private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputDirectoryLabel.setText(this.inputDirectory);
        outputDirectoryLabel.setText(this.outputDirectory);
        autoChoosePreviewImage.setAllowIndeterminate(false);
        autoChoosePreviewImage.setSelected(true);
        previewImageChooser.setDisable(true);
    }

    @FXML
    private void openDirectoryChooser(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if(selectedDirectory != null){

            // input directory chooser
            if (event.getSource().equals(inputDirectoryChooser)) {
                inputImagePaths = new ArrayList<>();
                this.inputDirectory = selectedDirectory.getAbsolutePath();
                inputDirectoryLabel.setText(this.inputDirectory);
                inputDirectoryValidationStatus.setTextFill(new Color(1.0,1.0,1.0,0.5));
                inputDirectoryValidationStatus.setText("Checking...");
                String validationResult = inputDirectoryValidator(this.inputDirectory);
                if (validationResult.equals("")) {
                    inputDirectoryValidationStatus.setTextFill(new Color(0.0,1.0,0,0.8));
                    int numberOfDiscoveredImages = inputImagePaths.size();
                    inputDirectoryValidationStatus.setText(String.format("Found %d images", numberOfDiscoveredImages));
                    // load preview image
                } else {
                    inputDirectoryValidationStatus.setTextFill(new Color(1.0,0,0,1.0));
                    inputDirectoryValidationStatus.setText(validationResult);
                }
            }

            // output directory chooser
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

    @FXML
    private void setPreviewSource(ActionEvent event){
        if (autoChoosePreviewImage.isSelected()) {
            System.out.print("Will auto choose preview image\r");
            previewImageChooser.setDisable(true);
        } else {
            System.out.print("Will manually choose preview image\r");
            previewImageChooser.setDisable(false);
        }
    }

    private String inputDirectoryValidator(String inputDirectoryPath) {
        File folder = new File(inputDirectoryPath);
        File[] listOfFiles = folder.listFiles();
        int numOfFiles = listOfFiles.length;

        if (numOfFiles == 0) {
            return "Selected source directory does not contain any files";
        }

        for (int i = 0; i < listOfFiles.length; i++) {
            String imagePath = listOfFiles[i].getAbsolutePath();
            String fileExtention = FilenameUtils.getExtension(imagePath);
            if (listOfFiles[i].isFile() && SUPPORTED_FILE_TYPES.contains(fileExtention.toUpperCase())) {
                inputImagePaths.add(imagePath);
            }
        }

        if (inputImagePaths.size() == 0) {
            return "Could not find any files with supported formats in source directory";
        }

        return "";
    }
}