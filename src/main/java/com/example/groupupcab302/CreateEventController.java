package com.example.groupupcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.SQLException;
import java.time.LocalDate;


public class CreateEventController extends ParentViewController {

    private EventDAO eventDAO;
    private Stage stage;
    private File selectedFile;
    @FXML
    private TextField eventName;
    @FXML
    private TextField eventDescription;
    @FXML
    private DatePicker eventDate;
    @FXML
    private TextField eventRegistrationQuantity;
    @FXML
    private TextField eventLocation;
    @FXML
    private CheckBox termsAndConditions;
    @FXML
    private TextField eventGenre;
    @FXML
    private ImageView eventImage;
    @FXML
    private TextField eventTime;

    private String urlOfImageInMavenResourceFolder;
    private int eventRegistrationQuantityParsed;

    GroupUpUser eventCreator = new GroupUpUser(1, "username1", "John", "Doe", "john@example.com", "123456789", "25", "password1");

    private Scene scene;

    public CreateEventController(){
        eventDAO = new EventDAO();
    }
    @FXML
    public void createEvent() throws SQLException {
            try{
                System.out.println(urlOfImageInMavenResourceFolder);
                Event eventToBeCreated = new Event(eventCreator, eventName.getText(), eventDate.getValue().toString(), eventTime.getText(),
                        eventLocation.getText(), eventGenre.getText(), eventRegistrationQuantityParsed, eventDescription.getText(), urlOfImageInMavenResourceFolder);

                eventDAO.insert(eventToBeCreated);
            }
            catch (CustomSQLException e) {
                throw new RuntimeException(e);
            }

    }


    @FXML
    public void submit() throws CustomSQLException {
        try {
            if (termsAndConditions.isSelected()) {
                // Change the text below to show in status field
                System.out.println("Working on Event...");

                // Define the local directory where the image will be stored
                File localDirectory = new File("src\\main\\resources\\com\\example\\groupupcab302\\Images");

                // Check if the local directory does not exist
                if (!localDirectory.exists()) {
                    // If the directory doesn't exist, handle the situation (e.g., communicate to the user)
                    // and return from the method
                    // (Note: This is commented out, so it won't be executed)
                    // create a status text area box
                    return;
                }

                // Check if a file is selected
                if (selectedFile != null) {
                    try {
                        // Get the path of the selected file
                        Path sourcePath = selectedFile.toPath();

                        // Create a destination path for the file by combining the local directory and the filename
                        Path destinationPath = new File(localDirectory, selectedFile.getName()).toPath();

                        // Copy the selected file from its source path to the destination path,
                        // replacing the file if it already exists
                        // Replacing is not an issue, other events which use the same image will still point to the same image
                        // However, images which are different but with the same name will need to be looked into...
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        // Construct the URL for the image within the Maven resource folder
                        // by combining the folder path with the filename
                        urlOfImageInMavenResourceFolder = "/com/example/groupupcab302/Images/" + selectedFile.getName();
                        createEvent();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        catch (SQLException sqlException){
            System.out.println("There was an issue" + sqlException);
        }
    }

    @FXML
    public void selectPhoto() throws CustomSQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");
        fileChooser.setInitialDirectory(new File("C://"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"), new FileChooser.ExtensionFilter("PNG image", "*.png"), new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"));
        selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());
            eventImage.setImage(image);
        }else{
            System.out.println("No file has been selected");
        }
    }

}
