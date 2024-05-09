package com.example.groupupcab302;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ParentViewController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private UserInformation userInformation = new UserInformation();

    @FXML
    protected void onNavCreateEventClick(ActionEvent event) {
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
    protected void onLoginButtonClick(ActionEvent event) {
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
    protected void onSignUpButtonClick(ActionEvent event) {
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
    protected void onNavYourEventsClick(ActionEvent event) {
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
    protected void onNavLogOutClick(ActionEvent event) {
        try {
            // Basic code to switch the scene to an appropriate scene
            Parent root = FXMLLoader.load(getClass().getResource("start-screen.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Unfortunately there was an issue when trying to redirect you to the start screen!");
        }
    }

    @FXML
    protected void onNavDiscoverEventClick(ActionEvent event) {
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
    protected void onNavRegistrationClick(ActionEvent event) {
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


}
