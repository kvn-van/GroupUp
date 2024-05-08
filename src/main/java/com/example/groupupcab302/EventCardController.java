package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EventCardController {

    @FXML
    private Label eventName;

    @FXML
    private Label eventDate;

    @FXML
    private Label eventLocation;

    @FXML
    private Label eventRegistrationSpots;

    @FXML
    private Button eventViewButton;

    @FXML
    private ImageView eventImage;

    public void setData(Event event){
        /*String imageURL = event.getImage();
        Image image = new Image(getClass().getResourceAsStream(imageURL));
        eventImage.setImage(image);*/
        eventName.setText(event.getName());
        eventDate.setText(event.getDate() + " " + event.getTime());
        eventLocation.setText(event.getLocation());
        eventRegistrationSpots.setText("Only " + event.getNumberOfRegistrationsAvailable() + " Spots left!");

    }

}
