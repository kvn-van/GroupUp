package com.example.groupupcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.SQLException;


public class CreateEventController extends ParentViewController {
    private UserInformation userInformation = new UserInformation();
    private EventDAO eventDAO;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private Stage stage;
    public File selectedFile;
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
    @FXML
    private Text ErrorText;

    private String urlOfImageInMavenResourceFolder;


    private String eventRegistrationQuantityParsed;

    private Scene scene;


    public CreateEventController(){
        eventDAO = new EventDAO();

    }
    @FXML
    public void createEvent() throws SQLException {
            try{
                // Retrieve the user who is currently logged in to use their ID and associate it with event being created
                Event eventToBeCreated = new Event(userInformation.getLoggedInUserInformation(), eventName.getText(), eventDate.getValue().toString(), eventTime.getText(),
                        eventLocation.getText(), eventGenre.getText(), eventRegistrationQuantity.getText(), eventDescription.getText(), urlOfImageInMavenResourceFolder);

                eventDAO.insert(eventToBeCreated);
            }
            catch (CustomSQLException e) {
                throw new RuntimeException(e);
            }

    }


    @FXML
    public void submit(ActionEvent event ) throws CustomSQLException {
        try {
            if (termsAndConditions.isSelected()) {
                ErrorText.setText("Working on Event...");

                if (!errorChecks()){
                    return;
                }

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
                        redirectToEventDiscoveryPage(event);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                ErrorText.setText("Please agree to the terms and conditions!");
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

    public boolean errorChecks() {
        if (eventName.getText().trim().isEmpty()) {
            ErrorText.setText("Event name cannot be empty!");
            return false;
        } else if (eventDescription.getText().trim().isEmpty()) {
            ErrorText.setText("Event description cannot be empty!");
            return false;
        } else if (eventDate.getValue() == null) {
            ErrorText.setText("Event date cannot be empty!");
            return false;
        } else if (!isValidEventTimeFormat(eventTime.getText())) {
            ErrorText.setText("Event time must be in 24 hour time (00:00)!");
            return false;
        } else if (eventLocation.getText().trim().isEmpty()) {
            ErrorText.setText("Event location cannot be empty!");
            return false;
        } else if (eventGenre.getText().trim().isEmpty()) {
            ErrorText.setText("Event genre cannot be empty!");
            return false;
        } else if (eventRegistrationQuantity.getText().trim().isEmpty()) {
            ErrorText.setText("Event registration quantity cannot be empty!");
            return false;
        } try {
            int eventQuantity = Integer.parseInt(eventRegistrationQuantity.getText());
        } catch (NumberFormatException e) {
            ErrorText.setText("Event registration quantity must be a number!");
            return false;
        }

        return true;
    }

    private boolean isValidEventTimeFormat(String rawTime){
        try {
            LocalTime parsedTime = LocalTime.parse(rawTime, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
