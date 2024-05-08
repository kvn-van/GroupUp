package com.example.groupupcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EventViewController implements Initializable {
    @FXML
    private GridPane eventGrid;
    private EventDAO eventDAO = new EventDAO();
    private List<Event> eventList;

    private void initializeEventList() throws SQLException{
            eventList = eventDAO.getAllEvents();
    }


    @FXML
    public Label eventDate1, eventDate2, eventDate3, eventDate4;

    @FXML
    public Label eventGenre1, eventGenre2, eventGenre3, eventGenre4;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Label eventName1, eventName2, eventName3, eventName4;
    @FXML
    public Label eventLocation1, eventLocation2, eventLocation3, eventLocation4;

     @Override
    public void initialize(URL location, ResourceBundle resources) {
         try {
             int columns = 0;
             int rows = 1;

             initializeEventList();

             for (int counter = 0; counter<eventList.size() ; counter++){
                 FXMLLoader fxmlLoader = new FXMLLoader();
                 fxmlLoader.setLocation(getClass().getResource("event-cards.fxml"));

                 VBox eventCardPlacementBox = fxmlLoader.load();

                 EventCardController eventCardController = fxmlLoader.getController();
                 eventCardController.setData(eventList.get(counter));

                 if (columns == 3){
                     columns = 0;
                     ++rows;
                 }

                 eventGrid.add(eventCardPlacementBox, columns++, rows);
                 GridPane.setMargin(eventCardPlacementBox, new Insets(10));
             }

         } catch (SQLException sqlException) {
             System.out.println("There was an issue when trying to get all events." + sqlException);

         } catch (IOException e) {
             e.printStackTrace();
         }
     }




    @FXML
    protected void onNavEventCreateClick(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("event-create-template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void displayEventName(int eventID, Label eventName) {
        try {
            String eventNameString = eventDAO.getEventNameById(eventID);
            eventName.setText(Objects.requireNonNullElse(eventNameString, "Event not found"));
        } catch (SQLException e) {
            eventName.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    private void displayDate(int eventID, Label eventDate) {
        try {
            String eventDateString= eventDAO.getDateById(eventID);
            eventDate.setText(Objects.requireNonNullElse(eventDateString, "Event not found"));
        } catch (SQLException e) {
            eventDate.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    private void displayLocation(int eventID, Label eventLocation) {
        try {
            String eventLocationString  = eventDAO.getLocationById(eventID);
            eventLocation.setText(Objects.requireNonNullElse(eventLocationString, "Event not found"));
        } catch (SQLException e) {
            eventLocation.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }

    private void displayGenre(int eventID, Label eventGenre) {
        try {
            String eventGenreString = eventDAO.getGenreById(eventID);
            eventGenre.setText(Objects.requireNonNullElse(eventGenreString, "Event not found"));
        } catch (SQLException e) {
            eventGenre.setText("Error retrieving event");
            System.out.println(e.getMessage());
        }
    }
}