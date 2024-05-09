package com.example.groupupcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class EditingEventController extends ParentViewController {

    // Rename location to have a 1 to prevent random FXML conflict?
    @FXML
    private TextField location1;
    //Rename the @fxml elements to exactly match the naming of DB columns for simplicitiy when updating
    @FXML
    private Button REGISTERBUTTON;

    @FXML
    private DatePicker date;

    private EventDAO eventDAO = new EventDAO();

    @FXML
    private TextField descriptionOfEvent;

    @FXML
    private Text eventID;

    @FXML
    private TextField genre;

    @FXML
    private TextField name;

    @FXML
    private TextField numberOfRegistrationsAvailable;

    @FXML
    private TextField time;

    @FXML
    private AnchorPane eventDetailsContainer;

    private UserInformation userInformation = new UserInformation();

    private Event eventSelected;

    public void initialize(){
        eventSelected = userInformation.getEventSelectedByUser();
        eventID.setText("You Are Currently Editing Event ID: " + eventSelected.getEventID());
        name.setText(eventSelected.getName());
        genre.setText(eventSelected.getGenre());
        location1.setText(eventSelected.getLocation());
        descriptionOfEvent.setText(eventSelected.getDescription());
        time.setText(eventSelected.getTime());
        date.setValue(LocalDate.parse(eventSelected.getDate()));
        numberOfRegistrationsAvailable.setText(eventSelected.getNumberOfRegistrationsAvailable());
    }

    @FXML
    public void onSubmit(){
        System.out.println("working");
        update();
    }

    private void update() {
        try {
            // Iterate through all the child nodes of the eventDetailsContainer
            for (Node node : eventDetailsContainer.getChildren()) {
                // Check if the node is a TextField
                if (node instanceof TextField) {
                    // Cast the node to a TextField
                    TextField textField = (TextField) node;
                    // Get the ID of the TextField (assuming it's set in the FXML)
                    String attributeOfEventToUpdate = textField.getId();
                    // If the ID is "location1", rename it to match the database column name
                    if (Objects.equals(attributeOfEventToUpdate, "location1")){
                        attributeOfEventToUpdate = "location";
                    }
                    // Get the text entered into the TextField
                    String valueToSetAttributeTo = textField.getText();

                    // Call the update method with the attribute name and value
                    eventDAO.update(eventSelected, attributeOfEventToUpdate, valueToSetAttributeTo);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Enter a detailed message here for reason of error and what user did wrong" + sqlException);
        }
    }

}
