package com.example.groupupcab302;

import javafx.fxml.FXML;
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

import java.time.LocalDate;


public class CreateEventController {

    public EventDAO EventDA;
    private Stage stage;
    private File selectedFile;
    @FXML
    private TextField NAMETEXT;
    @FXML
    private TextField EVENTTEXT;
    @FXML
    private DatePicker DATETEXT;
    @FXML
    private TextField GUESTLIMIT;
    @FXML
    private TextField LOCATIONTEXT;
    @FXML
    private CheckBox TERMSBUTTON;
    @FXML
    private TextField GENRETEXT;
    @FXML
    private ImageView IMAGEVIEW;

    int EventUserId = 9;
    String EventName;

    LocalDate EventDate;
    String Location;
    String Summary;
    String Genre;
    int GuestLimit;
    String Image;

    public CreateEventController(){
        EventDA = new EventDAO();
        stage = new Stage();
    }
    @FXML
    public void createEvent(){
        Event event = new Event(1,EventName, EventDate,  0, Location,Genre,GuestLimit, Summary,Image);


            try{
                EventDA.insert(event);
            }
            catch (CustomSQLException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    public void submit() throws CustomSQLException {
        if (TERMSBUTTON.isSelected()) {
            System.out.println("Working on Event...");
            EventName = NAMETEXT.getText();
            EventDate = DATETEXT.getValue();
            Location = LOCATIONTEXT.getText();
            Summary = EVENTTEXT.getText();
            Genre = GENRETEXT.getText();
            GuestLimit = Integer.parseInt(GUESTLIMIT.getText());


            //For the Image
            File localDirectory = new File("EventImages");

            if (!localDirectory.exists()){
                localDirectory.mkdirs();
            }

            if (selectedFile != null) {
                try {
                    Path sourcePath = selectedFile.toPath();
                    Path destinationPath = new File(localDirectory, selectedFile.getName()).toPath();
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                    Image = destinationPath.toAbsolutePath().toString();
                    System.out.println("Image copied successfully to: " + destinationPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            createEvent();
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
            IMAGEVIEW.setImage(image);
        }else{
            System.out.println("No file has been selected");
        }
    }
}
