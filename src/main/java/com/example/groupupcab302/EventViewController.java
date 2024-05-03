package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.groupupcab302.LoginController.pageID;

public class EventViewController {

    @FXML
    public Label eventName1, eventName2, eventName3, eventName4;
    @FXML
    public Label eventLocation1, eventLocation2, eventLocation3, eventLocation4;

    @FXML
    public Label eventDate1, eventDate2, eventDate3, eventDate4;

    @FXML
    public Label eventGenre1, eventGenre2, eventGenre3, eventGenre4;
    public Button viewEvent;

    public static int viewEventNumber;

    private final EventDAO eventDAO = new EventDAO();
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
    public void displayEventName(int eventID, Label eventName) {
        try {
            String eventNameString = eventDAO.getEventNameById(eventID);
            eventName.setText(Objects.requireNonNullElse(eventNameString, "Event not found"));
        } catch (SQLException e) {
            eventName.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    public void displayDate(int eventID, Label eventDate) {
        try {
            String eventDateString= eventDAO.getDateById(eventID);
            eventDate.setText(Objects.requireNonNullElse(eventDateString, "Event not found"));
        } catch (SQLException e) {
            eventDate.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    public void displayLocation(int eventID, Label eventLocation) {
        try {
            String eventLocationString  = eventDAO.getLocationById(eventID);
            eventLocation.setText(Objects.requireNonNullElse(eventLocationString, "Event not found"));
        } catch (SQLException e) {
            eventLocation.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    public void displayGenre(int eventID, Label eventGenre) {
        try {
            String eventGenreString = eventDAO.getGenreById(eventID);
            eventGenre.setText(Objects.requireNonNullElse(eventGenreString, "Event not found"));
        } catch (SQLException e) {
            eventGenre.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void setViewEvent1() throws IOException {
         viewEventNumber = 1;
         setViewEventDetail(viewEventNumber);
    }

    @FXML
    public void setViewEvent2() throws IOException {
         viewEventNumber = 2;
         setViewEventDetail(viewEventNumber);
    }

    @FXML
    public void setViewEvent3() throws IOException {
         viewEventNumber = 3;
         setViewEventDetail(viewEventNumber);
    }

    @FXML
    public void setViewEvent4() throws IOException {
         viewEventNumber = 4;
         setViewEventDetail(viewEventNumber);
    }

    @FXML
    protected void setViewEventDetail(int viewEventNumber) throws IOException {
        pageID = "event-detail-page.fxml";
        LoginController.changeScene(viewEvent, pageID);
    }
}
