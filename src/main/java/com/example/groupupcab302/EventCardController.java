package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class EventCardController extends ParentViewController{

    @FXML
    private Label eventName;

    @FXML
    private Label eventDate;

    @FXML
    private Label eventLocation;

    @FXML
    private Label eventRegistrationSpots;

    @FXML
    private ImageView eventImage;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private EventDAO eventDAO = new EventDAO();
    private UserInformation userInformation = new UserInformation();


    // Store the event retrieved from the DB and its details so that the details can be passed to other screens
    private Event event;

    // Using the layout of the card, allow an event to be supplied as a parameter
    // Allows the card to display the content/data of the event
    public void setData(Event event){
        // Store event details first
        this.event = event;
        System.out.println(event);

        // Get the relative url path for the events image and load it
        // Since project is maven dependency and resource folder is marked as resources root no need to ../ from current directory
        // Always use the path from source root i.e from the resources folder
        // All events store their images in resources /Images
        String imageURL = event.getImage();

        // Get the image as a stream of bytes
        Image image = new Image(getClass().getResourceAsStream(imageURL));

        // Image view on layout has preserve ratio disabled meaning all images will stretch to the size of image view container
        eventImage.setImage(image);

        // Set content of the card with other details
        eventName.setText(event.getName());
        eventDate.setText(event.getDate() + " " + event.getTime());
        eventLocation.setText(event.getLocation());
        eventRegistrationSpots.setText("Only " + event.getNumberOfRegistrationsAvailable() + " Spots left!");
    }

    @FXML
    //Rename the paramater to differentiate from the event object
    public void onEventCardClick(ActionEvent actionableEvent) throws IOException {

        userInformation.setEventSelectedByUser(this.event);
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-create.fxml"));
        stage = (Stage)((Node)actionableEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
