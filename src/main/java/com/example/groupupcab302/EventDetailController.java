package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.groupupcab302.LoginController.changeScene;
import static com.example.groupupcab302.LoginController.pageID;

public class EventDetailController {

    public Button goBack;

    @FXML
    public Label eventName, eventLocation, eventDate, eventGenre, eventDescription, eventRegNumber;

    private final EventDAO eventDAO = new EventDAO();

    private final EventViewController eventViewController = new EventViewController();

    @FXML
    private void initialize() {
        eventViewController.displayEventName(eventViewController.viewEventNumber, eventName);
        eventViewController.displayLocation(eventViewController.viewEventNumber, eventLocation);
        eventViewController.displayDate(eventViewController.viewEventNumber, eventDate);
        eventViewController.displayGenre(eventViewController.viewEventNumber, eventGenre);
        displayRegNumber(eventViewController.viewEventNumber, eventRegNumber);
        displayDescription(eventViewController.viewEventNumber, eventDescription);
    }

    public void displayRegNumber(int eventID, Label eventRegNumber) {
        try {
            String eventRegNumberString  = eventDAO.getRegNumberById(eventID);
            eventRegNumber.setText(Objects.requireNonNullElse(eventRegNumberString, "Event not found"));
        } catch (SQLException e) {
            eventRegNumber.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    public void displayDescription(int eventID, Label eventDescription) {
        try {
            String eventDescriptionString  = eventDAO.getDescriptionById(eventID);
            eventDescription.setText(Objects.requireNonNullElse(eventDescriptionString, "Event not found"));
        } catch (SQLException e) {
            eventDescription.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void goBackBtn() throws IOException {
        pageID = "event-view-template.fxml";
        LoginController.changeScene(goBack, pageID);
    }
}
