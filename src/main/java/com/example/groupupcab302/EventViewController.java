package com.example.groupupcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EventViewController {

    @FXML
    public Label eventName1, eventName2, eventName3, eventName4;
    @FXML
    public Label eventLocation1, eventLocation2, eventLocation3, eventLocation4;

    @FXML
    public Label eventDate1, eventDate2, eventDate3, eventDate4;

    @FXML
    public Label eventGenre1, eventGenre2, eventGenre3, eventGenre4;

    private Stage stage;
    private Scene scene;
    private Parent root;

     private EventDAO eventDAO = new EventDAO();
     @FXML
    private void initialize() {
        displayEventName(1, eventName1);
        displayEventName(2, eventName2);
        displayEventName(3, eventName3);
        displayEventName(4, eventName4);

        displayLocation(1, eventLocation1);
        displayLocation(2, eventLocation2);
        displayLocation(3, eventLocation3);
        displayLocation(4, eventLocation4);

        displayDate(1, eventDate1);
        displayDate(2, eventDate2);
        displayDate(3, eventDate3);
        displayDate(4, eventDate4);

        displayGenre(1, eventGenre1);
        displayGenre(2, eventGenre2);
        displayGenre(3, eventGenre3);
        displayGenre(4, eventGenre4);
    }




    @FXML
    protected void onNavEventCreateClick(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-create-template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void displayEventName(int eventID, Label eventName) {
        try {
            String eventNameString = eventDAO.getEventNameById(eventID);
            eventName.setText(Objects.requireNonNullElse(eventNameString, "Event not found"));
        } catch (SQLException e) {
            eventName.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    private void displayDate(int eventID, Label eventDate) {
        try {
            String eventDateString= eventDAO.getDateById(eventID);
            eventDate.setText(Objects.requireNonNullElse(eventDateString, "Event not found"));
        } catch (SQLException e) {
            eventDate.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    private void displayLocation(int eventID, Label eventLocation) {
        try {
            String eventLocationString  = eventDAO.getLocationById(eventID);
            eventLocation.setText(Objects.requireNonNullElse(eventLocationString, "Event not found"));
        } catch (SQLException e) {
            eventLocation.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    private void displayGenre(int eventID, Label eventGenre) {
        try {
            String eventGenreString = eventDAO.getGenreById(eventID);
            eventGenre.setText(Objects.requireNonNullElse(eventGenreString, "Event not found"));
        } catch (SQLException e) {
            eventGenre.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }
}