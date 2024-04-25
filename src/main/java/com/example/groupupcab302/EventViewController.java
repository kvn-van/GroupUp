package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.SQLException;
import java.util.Objects;

public class EventViewController {

    @FXML
    public Label eventLabel1;
    @FXML
    public Label eventLabel2;
    @FXML
    public Label eventLabel3;
    @FXML
    public Label eventLabel4;

    private EventDAO eventDAO = new EventDAO();

    @FXML
    private void initialize() {
        displayEventName(1, eventLabel1);
        displayEventName(2, eventLabel2);
        displayEventName(3, eventLabel3);
        displayEventName(4, eventLabel4);
    }

    @FXML
    private void displayEventName(int eventID, Label eventLabel) {
        try {
            String eventName = eventDAO.getEventNameById(eventID);
            eventLabel.setText(Objects.requireNonNullElse(eventName, "Event not found"));
        } catch (SQLException e) {
            eventLabel.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }
}