package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class CreateEventController {

    private EventDAO EventDAO;

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
    private CheckBox TermsButton;

    int EventUserId = 9;
    String EventName;

    LocalDate EventDate;
    String Location;
    String Summary;
    int GuestLimit;

    @FXML
    public void createEvent(){
        Event event = new Event(1,EventName, EventDate,  0, Location,"SAdasdasd",GuestLimit, Summary,0);


            try{
                EventDAO.insert(event);
            }
            catch (CustomSQLException e) {
                throw new RuntimeException(e);
            }
    }
    @FXML
    public void submit() throws CustomSQLException {
        if (TermsButton.isSelected()) {
            System.out.println("Working on Event...");
            EventName = NameText.getText();
            EventDate = DateField.getValue();
            Location = LocationField.getText();
            Summary = SummaryField.getText();
            GuestLimit = Integer.parseInt(GuestField.getText());

            createEvent();
        }
    }
}
