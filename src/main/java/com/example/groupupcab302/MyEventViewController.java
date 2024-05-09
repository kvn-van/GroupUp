package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MyEventViewController extends ParentViewController implements IDisplayingEvent  {

    @FXML
    private GridPane eventGrid;

    private UserInformation userInformation = new UserInformation();
    private EventDAO eventDAO = new EventDAO();
    private List<Event> eventList;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        showEventsFromDB();
    }

    // Modify the code to only show events which the user signed up to
    public void showEventsFromDB() {
        try {
            eventList = eventDAO.getAllEvents();
            int columns = 0;
            int rows = 1;
            for (int counter = 0; counter<eventList.size() ; counter++){
                // Create an fxml loader object
                String listOfAttendees = eventList.get(counter).getEventAttendees();
                if (listOfAttendees != null){
                    if (listOfAttendees.contains(userInformation.getLoggedInUserInformation().getEmail())){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        // Use the fxml loader object to load the event card fxml doc to retrieve the structure
                        fxmlLoader.setLocation(getClass().getResource("event-cards.fxml"));

                        // Create a vbox to hold the structure returned by the event card fxml file upon load
                        VBox eventCardPlacementBox = fxmlLoader.load();

                        // Retrieve the controller associated with the event card fxml
                        EventCardController eventCardController = fxmlLoader.getController();
                        // Set the content of the card by supplying the data of an event retrieved from the database
                        eventCardController.setData(eventList.get(counter));

                        // Check that the number of event cards created does not exceed 3 per row
                        if (columns == 3){
                            // Reset the number to 0 to start placing cards at the start of the row
                            columns = 0;
                            // Increment the rows to move down 1 before starting to place the next event cards
                            ++rows;
                        }
                        // Add the event card to the actual grid pane for display
                        eventGrid.add(eventCardPlacementBox, columns++, rows);
                        // Create a margin between all event cards of 10 pixels for all sides
                        GridPane.setMargin(eventCardPlacementBox, new Insets(10));
                        continue;
                    }
                }
            }

        } catch (SQLException sqlException) {
            System.out.println("There was an issue when trying to get all events." + sqlException);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
