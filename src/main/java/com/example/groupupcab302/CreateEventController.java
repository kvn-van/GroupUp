package com.example.groupupcab302;

import com.example.groupupcab302.mockDAO.MockEventDAO;
import com.example.groupupcab302.mockDAO.MockUserDAO;
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

import java.time.LocalDate;


public class CreateEventController {

    public EventDAO EventDA;

    public MockEventDAO MockEventDA;
    private Stage stage;
    public File selectedFile;
    @FXML
    public TextField NAMETEXT;
    @FXML
    public  TextField EVENTTEXT;
    @FXML
    public  DatePicker DATETEXT;
    @FXML
    public  TextField GUESTLIMIT;
    @FXML
    public  TextField LOCATIONTEXT;
    @FXML
    public  CheckBox TERMSBUTTON;
    @FXML
    public TextField GENRETEXT;
    @FXML
    public  ImageView IMAGEVIEW;

    int EventUserId = 9;
    String EventName;

    String EventDate;
    String EventTime;
    String Location;
    String Summary;
    String Genre;
    int GuestLimit;
    String Image;
    private Scene scene;
    private Parent root;

    public CreateEventController(){
        EventDA = new EventDAO();
        stage = new Stage();
    }
    @FXML
    public void createEvent(){
        System.out.println("DO NOTHIBNG FOR NOW");

        /*
        Event event = new Event(1,EventName, EventDate,  EventTime, Location,Genre,GuestLimit, Summary,Image);

            try{
                EventDA.insert(event);
            }
            catch (CustomSQLException e) {
                throw new RuntimeException(e);
            }

         */
    }

    @FXML
    public void submit() throws CustomSQLException {
        System.out.println("do nothing for now");
        /*
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

         */
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

    @FXML
    protected void onNavEventPage(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-view-template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onNavCreatePage(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-create-template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onNavRegisterPage(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("Sign-Up-Page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
