package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;


public class CreateEventController {

    private EventDAO EventDao;
    @FXML
    private TextField NameText;
    @FXML
    private TextField SummaryField;
    @FXML
    private DatePicker DateField;
    @FXML
    private TextField GuestField;
    @FXML
    private TextField LocationField;
    @FXML
    private Button RegisterButton;
    @FXML
    private CheckBox TermsButton;

    int EventUserId = 9;
    String EventName;

    LocalDate EventDate;
    String Location;
    String Summary;
    int GuestLimit;

    public void createEvent() throws CustomSQLException {
        Event Event = new Event(1,EventName, EventDate,  0, Location,"SAdasdasd",GuestLimit, Summary,0);

        EventDao.insert(Event);
    }
    @FXML
    public void submit() throws CustomSQLException {
        System.out.println("Working on Event...");
        EventName = NameText.getText();
        EventDate = DateField.getValue();
        Location = LocationField.getText();
        Summary = SummaryField.getText();
        GuestLimit = Integer.parseInt(GuestField.getText());

        createEvent();
    }
}
