package com.example.groupupcab302;
import com.example.groupupcab302.DAO.EventDAO;
import com.example.groupupcab302.Objects.Event;
import com.example.groupupcab302.Objects.GroupUpUser;
import javafx.event.ActionEvent;
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


    private UserInformationController userInformationController = new UserInformationController();

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
            eventChosenByUser = userInformationController.getEventSelectedByUser();

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

    @FXML
    public void goBackBtn() throws IOException {
        pageID = "event-view-template.fxml";
        changeScene(goBack, pageID);
    }

    @FXML
    public void joinEvent(ActionEvent event){
        try{
            //Retrieve the event from the DB
            Event eventFromDB = eventDAO.getEventById(eventChosenByUser.getEventID());
            // Get the current list of attendees
            String listOfAttendees = eventFromDB.getEventAttendees();
            if (checkIfRegistrationSpotAvailable()){
                registerUserToEvent(listOfAttendees, event);
            }
            else{
                displayNotification("Registration Error", "Unfortunately there isnt enough available " +
                        "spots to register you for this event", true );
            }

        } catch (SQLException exception){
            displayNotification("Registration Error", "There was an issue when trying to add the user to the " +
                    "list of attendees \nor reducing the number of available registrations!" + exception, true );
        }
    }

    @FXML
    public void leaveEvent(ActionEvent event){
        try{
            //Retrieve the event from the DB
            Event eventFromDB = eventDAO.getEventById(eventChosenByUser.getEventID());
            // Get the current list of attendees
            String listOfAttendees = eventFromDB.getEventAttendees();
            unregisterUserFromEvent(listOfAttendees, event);

        } catch (SQLException exception){
            displayNotification("Unregistration Error", "There was an issue when trying \nto remove you from the" +
                    "list of attendees.\n" + exception, true );
        }
    }

    private boolean checkIfRegistrationSpotAvailable() throws SQLException {
        //Retrieve the event from the DB
        Event eventFromDB = eventDAO.getEventById(eventChosenByUser.getEventID());
        // Get the current number of registrations available
        // registration number already parsed/validated as only containing numeric characters before insertion
        // Parsing is safe
        int numOfRegistrationsAvailable = Integer.parseInt(eventFromDB.getNumberOfRegistrationsAvailable());

        if (numOfRegistrationsAvailable > 0){
            return true;
        }
        return false;

    }

    private boolean isListOfAttendeesEmpty(String listOfEventAttendees){
        return listOfEventAttendees == null;
    }

    private boolean isUserAlreadyRegisteredToEvent(String listOfEventAttendees){
        return listOfEventAttendees.contains(userInformationController.getLoggedInUserInformation().getEmail());
    }

    // Too complex to manage the registration of a user in one event due to validation checks on user existence in string
    // If user in string they can neither register which is expected but also cant unregister
    private void registerUserToEvent(String listOfEventAttendees, ActionEvent event) throws SQLException {
        try{
            GroupUpUser userToManage = userInformationController.getLoggedInUserInformation();
            if (isListOfAttendeesEmpty(listOfEventAttendees) || !isUserAlreadyRegisteredToEvent(listOfEventAttendees)) {
                listOfEventAttendees += "," + userToManage.getEmail();
                eventDAO.update(eventChosenByUser, "eventAttendees", listOfEventAttendees);
                eventDAO.manageRegistrationsAvailable("Subtraction", eventChosenByUser);
                displayNotification("Registration Success", "You have successfully registered for the event!", false);
                redirectToRegisteredForEventsPage(event);
            }
            else {
                displayNotification("Registration Error", "You are already registered for this event!", true );
            }
        }

        catch (CustomSQLException customSQLException){
            displayNotification("Event Error", customSQLException.getMessage(), true );
        }

    }

    private void unregisterUserFromEvent(String listOfEventAttendees, ActionEvent event) throws SQLException {
        try{
            GroupUpUser userToManage = userInformationController.getLoggedInUserInformation();
            if (isListOfAttendeesEmpty(listOfEventAttendees) || isUserAlreadyRegisteredToEvent(listOfEventAttendees)) {
                listOfEventAttendees = listOfEventAttendees.replace(userToManage.getEmail(), "");
                eventDAO.update(eventChosenByUser, "eventAttendees", listOfEventAttendees);
                eventDAO.manageRegistrationsAvailable("Addition", eventChosenByUser);
                displayNotification("Unregistration Success", "You have successfully unregistered from the event!", false);
                redirectToEventDiscoveryPage(event);
            } else {
                displayNotification("Unregistration Error", "You are not registered for this event!", true );
            }
        }

        catch (CustomSQLException customSQLException){
            displayNotification("Event Error", customSQLException.getMessage(), true );
        }

    }
}
