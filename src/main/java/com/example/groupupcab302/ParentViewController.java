package com.example.groupupcab302;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Parent root;

    private UserInformation userInformation = new UserInformation();

    @FXML
    protected void redirectToEventCreatePage(ActionEvent event) {
        try {
            // Load the FXML file describing the new scene
            Parent root = FXMLLoader.load(getClass().getResource("event-create.fxml"));
            // Get the stage (window) where the event occurred
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            // Create a new scene with the loaded content
            Scene scene = new Scene(root);
            // Set the newly created scene to the stage
            stage.setScene(scene);
            // Show the stage with the new scene
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred while loading the event creation scene.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void redirectToLoginPage(ActionEvent event) {
        try {
            // Load the FXML file describing the login page
            Parent root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
            // Get the stage (window) where the event occurred
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            // Create a new scene with the loaded content
            Scene scene = new Scene(root);
            // Set the newly created scene to the stage
            stage.setScene(scene);
            // Show the stage with the new scene
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred while loading the login page.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void redirectToSignUpPage(ActionEvent event) {
        try {
            // Load the FXML file describing the sign-up page
            Parent root = FXMLLoader.load(getClass().getResource("sign-up-page.fxml"));
            // Get the stage (window) where the event occurred
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            // Create a new scene with the loaded content
            Scene scene = new Scene(root);
            // Set the newly created scene to the stage
            stage.setScene(scene);
            // Show the stage with the new scene
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred while loading the sign-up page.");
            e.printStackTrace();
        }
    }


    @FXML
    protected void redirectToYourEventsPage(ActionEvent event) {
        try {
            // Load the FXML file describing the new scene
            Parent root = FXMLLoader.load(getClass().getResource("my-created-events.fxml"));
            // Get the stage (window) where the event occurred
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            // Create a new scene with the loaded content
            Scene scene = new Scene(root);
            // Set the newly created scene to the stage
            stage.setScene(scene);
            // Show the stage with the new scene
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred while loading your events.");
        }
    }

    @FXML
    protected void redirectToStartScreenPage(ActionEvent event) {
        try {
            // Basic code to switch the scene to an appropriate scene
            Parent root = FXMLLoader.load(getClass().getResource("Login-Page.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            displayNotification("Logout Success", "You were successfully logged out " +
                    "from GroupUp!\nWe hope to see you again!", false);
        } catch (IOException e) {
            System.out.println("Unfortunately there was an issue when trying to redirect you to the start screen!");
        }
    }

    @FXML
    protected void redirectToEventDiscoveryPage(ActionEvent event) {
        try {
            // Basic code to switch the scene to an appropriate scene
            Parent root = FXMLLoader.load(getClass().getResource("event-view.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred while loading the event view scene.");
        }
    }

    @FXML
    protected void redirectToRegisteredForEventsPage(ActionEvent event) {
        try {
            // Basic code to switch the scene to an appropriate scene
            Parent root = FXMLLoader.load(getClass().getResource("my-registered-events.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred while loading your registered events.");
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
