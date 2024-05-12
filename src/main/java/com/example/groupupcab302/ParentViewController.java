package com.example.groupupcab302;
import com.example.groupupcab302.Constants.EventTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;


import java.io.File;
import java.io.IOException;

public class ParentViewController {

    private Stage stage;
    private Scene scene;
    @FXML
    private Parent root;

    private UserInformationController userInformationController = new UserInformationController();

    @FXML
    protected void redirectToEventCreatePage(ActionEvent event) {
        try {
            redirectToPage("event-create.fxml", event);
        }
        catch (IOException ioException) {
            System.out.println("An error occurred while loading the event creation scene.");
        }
    }

    @FXML
    protected void redirectToLoginPage(ActionEvent event) {
        try {
            redirectToPage("login-page.fxml", event);
        } catch (IOException e) {
            System.out.println("An error occurred while loading the login page.");
        }
    }

    @FXML
    protected void redirectToSignUpPage(ActionEvent event) {
        try {
            redirectToPage("sign-up-page.fxml", event);
        } catch (IOException e) {
            System.out.println("An error occurred while loading the sign-up page.");
            e.printStackTrace();
        }
    }


    @FXML
    protected void redirectToYourEventsPage(ActionEvent event) {
        try {
            // Reset users display event preference to ensure old preference isnt displayed on new render
            resetUsersEventDisplayPreference();
            // Check the type of element which called the action
            if (event.getSource() instanceof MenuItem){
                redirectToPageFromDropdown(event, "my-created-events.fxml");
            }
            else{
                redirectToPage("my-created-events.fxml", event);
            }


        } catch (IOException e) {
            System.out.println("An error occurred while loading your events.");
        }
    }

    // Identify the status of events which the user wants to display and extract from db based on option selected
    private void handleDropDownSelection(String nameOfPageToView){
        if (nameOfPageToView.contains("Cancelled")){
            userInformationController.setUserEventPreferences(EventTypes.CLOSED.getEventType());
        }

        else if (nameOfPageToView.contains("Completed")){
            userInformationController.setUserEventPreferences(EventTypes.COMPlETED.getEventType());
        }

        else{
            userInformationController.setUserEventPreferences(EventTypes.OPEN_FOR_REGISTRATION.getEventType());
        }
    }

    private void redirectToPage(String nameOfFxmlDOC, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nameOfFxmlDOC));
        // Get the stage (window) where the event occurred
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // Create a new scene with the loaded content
        Scene scene = new Scene(root);
        // Set the newly created scene to the stage
        stage.setScene(scene);
        // Show the stage with the new scene
        stage.show();
    }
    @FXML
    protected void redirectToStartScreenPage(ActionEvent event) {
        try {
            redirectToPage("start-screen.fxml", event);
            displayNotification("Logout Success", "You were successfully logged out " +
                    "from GroupUp!\nWe hope to see you again!", false);
            resetUsersEventDisplayPreference();
        } catch (IOException e) {
            System.out.println("Unfortunately there was an issue when trying to redirect you to the start screen!");
        }
    }

    private void resetUsersEventDisplayPreference(){
        userInformationController.setUserEventPreferences(null);
    }
    private void redirectToPageFromDropdown(ActionEvent event, String pageToRedirectTo) throws IOException {
        // Since the source is a MenuItem, cast it to MenuItem
        //Get the name of the screen to view to decide what should be rendered
        MenuItem menuItem = (MenuItem) event.getSource();
        handleDropDownSelection(menuItem.getText());
        // Get the parent context menu
        ContextMenu parentMenu = menuItem.getParentPopup();
        // Get the owner node of the context menu
        Node ownerNode = parentMenu.getOwnerNode();

        //Code essentially backtracks from item to its parent to then the entire node of the parent/entire page
        // Assuming the owner node is the intended source of the action
        Stage stage = (Stage) ownerNode.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(pageToRedirectTo));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void redirectToEventDiscoveryPage(ActionEvent event) {
        try {
            redirectToPage("event-view.fxml", event);

        } catch (IOException ioException) {
            System.out.println("An error occurred while loading the event view scene.");
        }
    }

    @FXML
    protected void redirectToRegisteredForEventsPage(ActionEvent event) {
        try {
            // Reset users display event preference to ensure old preference isnt displayed on new render
            resetUsersEventDisplayPreference();
            // Check the type of element which called the action
            if (event.getSource() instanceof MenuItem){
                redirectToPageFromDropdown(event, "my-registered-events.fxml");
            }
            else{
                userInformationController.setUserEventPreferences(EventTypes.OPEN_FOR_REGISTRATION.getEventType());
                redirectToPage("my-registered-events.fxml", event);
            }

        } catch (IOException e) {
            displayNotification("Registered Events Page Error", "Unfortunately there was an error when trying to load your registered events page", true);
        }
    }

    // Create a universal function to handle displaying personalised notifications for user
    // type of notification is either success or error
    protected void displayNotification(String title, String notificationDescription, boolean isNotificationAnError){
        // If error type no need for graphic, otherwise its a success. Supply custom img
        String imageLocation = isNotificationAnError ? null : "src/main/resources/com/example/groupupcab302/Images/successIcon.jpg";
        Image image = null;

        if (imageLocation != null){
            // Follow basic process to convert resource at path to file then to img
            File file = new File(imageLocation);
            image = new Image(file.toURI().toString());
        }

        // Define notification to notify user when they are performing too many actions
        Notifications excessNotification = Notifications.create()
                .title("Too Many Actions!")
                .text("Please slow down!")
                .graphic(new ImageView(image))
                .owner(scene)
                .hideAfter(Duration.seconds(2))
                .position(Pos.TOP_LEFT);

        Notifications notification = Notifications.create()
                .title(title)
                .text(notificationDescription)
                .graphic(new ImageView(image))
                // Suppyling any value will cause the notification to render to the javafx screen instead of whole primary (default) screen.
                .owner(scene)
                .hideAfter(Duration.seconds(6))
                .threshold(6,excessNotification)
                .position(Pos.TOP_LEFT);

        if (!isNotificationAnError) {
            // Show a successful notification graphic
            notification.show();
        }
        else {
            // Show an error notification graphic
            notification.showError();
        }
    }


}
