package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.groupupcab302.LoginController.changeScene;
import static com.example.groupupcab302.LoginController.pageID;

public class EventDetailController extends ParentViewController {


    @FXML
    private Label eventDate;

    @FXML
    private TextArea eventDescription;

    @FXML
    private Label eventGenre;

    @FXML
    private Label eventLocation;

    @FXML
    private Label eventName;

    @FXML
    private Label eventTime;

    @FXML
    private Button goBack;

    @FXML
    private Label numberOfRegistrationSpots;

    @FXML
    private Button joinEvent;

    @FXML
    private ImageView specificEventImage;

    @FXML
    private Text title;


    private UserInformation userInformation = new UserInformation();

    private final EventDAO eventDAO = new EventDAO();

    private Event eventChosenByUser;

    @FXML
    // In JavaFX, the initialize() method within a controller class acts as a setup routine.
    // It's not a constructor but gets called automatically after the FXML file is loaded
    // and all UI elements are injected using @FXML annotations. Here, you can perform
    // any necessary initialization tasks, like setting default values, configuring event
    // handlers, or binding properties.
    private void initialize() {
        try{
            eventChosenByUser = userInformation.getEventSelectedByUser();

            String imageURL = "src/main/resources" + eventChosenByUser.getImage();
            File file = new File(imageURL);
            // Convert the File object 'file' to a URI (Uniform Resource Identifier) object
            // Convert the URI object to a string representation
            // Use the string representation of the URI to create an Image object
            Image image = new Image(file.toURI().toString());
            // Image view on layout has preserve ratio disabled meaning all images will stretch to the size of image view container
            specificEventImage.setImage(image);

            eventName.setText(eventChosenByUser.getName());
            eventLocation.setText(eventChosenByUser.getLocation());
            eventDate.setText(eventChosenByUser.getDate());
            eventTime.setText(eventChosenByUser.getTime());
            eventGenre.setText(eventChosenByUser.getGenre());
            numberOfRegistrationSpots.setText("Only " + eventChosenByUser.getNumberOfRegistrationsAvailable() + " Spots left!");
            eventDescription.setText(eventChosenByUser.getDescription());
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void reduceRegistrationsAvailable() throws SQLException {
        //Retrieve the event from the DB
        Event eventFromDB = eventDAO.getEventById(eventChosenByUser.getEventID());
        // Get the current number of registrations avaialble
        // registration number already parsed/validated as only containg numeric characters before insertion
        // Parsing is safe
        int numOfRegistrationsAvailable = Integer.parseInt(eventFromDB.getNumberOfRegistrationsAvailable());
        numOfRegistrationsAvailable -= 1;
        eventDAO.update(eventChosenByUser, "numberOfRegistrationsAvailable",Integer.toString(numOfRegistrationsAvailable));
    }
    @FXML
    public void goBackBtn() throws IOException {
        pageID = "event-view-template.fxml";
        LoginController.changeScene(goBack, pageID);
    }

    @FXML
    public void joinEvent(){
        try{
            //Retrieve the event from the DB
            Event eventFromDB = eventDAO.getEventById(eventChosenByUser.getEventID());
            // Get the current list of attendees
            String listOfAttendees = eventFromDB.getEventAttendees();
            if (checkIfRegistrationSpotAvailable()){
                registerUserToEvent(listOfAttendees);
            }
            else{
                System.out.println("Unfortunately there isnt enough available spots to register you. This should be displayed to a status text area");
            }

        } catch (SQLException exception){
            System.out.println("There was an issue when trying to add the user to the " +
                    "list of attendees or reducing the available registrations!" + exception);
        }
    }

    private boolean checkIfRegistrationSpotAvailable() throws SQLException {
        //Retrieve the event from the DB
        Event eventFromDB = eventDAO.getEventById(eventChosenByUser.getEventID());
        // Get the current number of registrations avaialble
        // registration number already parsed/validated as only containg numeric characters before insertion
        // Parsing is safe
        int numOfRegistrationsAvailable = Integer.parseInt(eventFromDB.getNumberOfRegistrationsAvailable());

        if (numOfRegistrationsAvailable > 0){
            return true;
        }

        return false;

    }
    private void registerUserToEvent(String listOfEventAttendees) throws SQLException {
        if (isListOfAttendeesEmpty(listOfEventAttendees) || !isUserAlreadyRegisteredToEvent(listOfEventAttendees)){
            // String doesnt need to be nicely formatted since .contain will be used to check if user is or isnt attending later on
            listOfEventAttendees += "," + userInformation.getLoggedInUserInformation().getEmail();
            eventDAO.update(eventChosenByUser, "eventAttendees", listOfEventAttendees);
            //Reduce the number of available registrations by 1
            reduceRegistrationsAvailable();
        }

        else{
            System.out.println("User is already found in db, this should be shown to a status text area");
        }

    }

    private boolean isListOfAttendeesEmpty(String listOfEventAttendees){
        if (listOfEventAttendees == null){
            return true;
        }
        return false;
    }

    private boolean isUserAlreadyRegisteredToEvent(String listOfEventAttendees){
        if (listOfEventAttendees.contains(userInformation.getLoggedInUserInformation().getEmail())){
            return true;
        }
        return false;
    }
}
