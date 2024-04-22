package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CreateEventController {
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

    @FXML
    public void submit() {
        System.out.println("Working on Event...");
        String EventName = NameText.getText();
        LocalDate EventDate = DateField.getValue();
        String Location = LocationField.getText();
        String Summary = SummaryField.getText();
        int GuestLimit = Integer.parseInt(GuestField.getText());

        System.out.println("Event Name: " + EventName);
        System.out.println("Event Date: " + EventDate);
        System.out.println("Guest Limit: " + GuestLimit);
        System.out.println("Location: " + Location);
        System.out.println("Summary: " + Summary);
    }
}
