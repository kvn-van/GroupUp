package com.example.groupupcab302;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.io.IOException;

public class StartController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Parent fxml;
    @FXML
    private Button signUpNavButton;
    @FXML
    private Button loginNavButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Load vbox...");
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try { //problem is line 44: fxml = FXMLLoader... due to NullPointerException
                //System.out.println("Stage 1");
                fxml = FXMLLoader.load(getClass().getClassLoader().getResource("New-Log-In-Page.fxml"));
                //System.out.println("Stage 2");
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
                //System.out.println("Loaded vbox!");
            } catch(IOException ex) {

            }
        });
    }
    @FXML
    protected void open_login(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("New-Log-In-Page.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch(IOException ex) {

            }
        });
    }
    @FXML
    private void open_signup(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("New-Sign-Up-Page.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch(IOException ex) {

            }
        });
    }
}