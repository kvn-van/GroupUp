package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
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
        try{
            // Store event details first
            this.event = event;
            // Get the relative url path for the events image from DB
            // Since file is being used instead of getClass().getResourceAsStream an absolute file path must be supplied
            /*
            // When you use getClass().getResourceAsStream(), the resources are typically loaded from the classpath, and these resources may indeed be cached by the JVM or the classloader.
            // This means that if you add or change resources after the application has been started, those changes may not be immediately reflected because the resources are already loaded into memory.
            // Due to this resources may be reloaded dynamically without caching.

            // Using File, on the other hand, directly accesses the file system, bypassing the classpath and any caching mechanisms.
            // This ensures that changes to resources are immediately reflected because the file is read directly from disk each time it's accessed.
            */
            String imageURL = "src/main/resources" + event.getImage();
            File file = new File(imageURL);

            // Convert the File object 'file' to a URI (Uniform Resource Identifier) object
            // Convert the URI object to a string representation
            // Use the string representation of the URI to create an Image object
            Image image = new Image(file.toURI().toString());

            // Image view on layout has preserve ratio disabled meaning all images will stretch to the size of image view container
            eventImage.setImage(image);

            // Set content of the card with other details
            eventName.setText(event.getName());
            eventDate.setText(event.getDate() + " " + event.getTime());
            eventLocation.setText(event.getLocation());
            eventRegistrationSpots.setText("Only " + event.getNumberOfRegistrationsAvailable() + " Spots left!");
        }

        catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException);
        }

    }

    @FXML
    //Rename the paramater to differentiate from the event object
    public void onEventCardClick(ActionEvent actionableEvent) throws IOException {
        userInformation.setEventSelectedByUser(this.event);
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-detail-page.fxml"));
        stage = (Stage)((Node)actionableEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
