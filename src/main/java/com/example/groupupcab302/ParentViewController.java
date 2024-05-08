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

    @FXML
    protected void onNavCreateEventClick(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-create-template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onNavDiscoverEventClick(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-view-template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
