package com.example.groupupcab302.Objects;

import com.example.groupupcab302.misc.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Event {

    private int eventID;
    private int eventCreatorUserID;
    private String name;
    private String date;
    private String time;
    private String location;
    private String genre;
    private String numberOfRegistrationsAvailable;
    private String description;
    private String image;

    Connection connection = null;

    // Datatype for attendees cant be a list because it isnt supported by SQL
    // Adding to a list must involve retrieving current value and conctatenating a string
    private String eventAttendees;

    // Default constructor
    // Should be called for instances where an event is created with values from controller
    public Event(GroupUpUser groupUpUser, String name, String date, String time, String location, String genre, String numberOfRegistrationsAvailable, String description, String image) throws SQLException {
        getValidEventIDFromDB();
        eventCreatorUserID = groupUpUser.getUserID();
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.genre = genre;
        this.numberOfRegistrationsAvailable = numberOfRegistrationsAvailable;
        this.description = description;
        this.image = image;
    }

    // Added constructor for special cases where event can be expiticly be created with an ID
    // Should be used to create an event  when the data for an event is returned from the database
    public Event(int eventID, int userIDOfEventCreator, String name, String date, String time, String location, String genre, String numberOfRegistrationsAvailable, String description, String image, String listOfAttendees){
        this.eventID = eventID;
        eventCreatorUserID = userIDOfEventCreator;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.genre = genre;
        this.numberOfRegistrationsAvailable = numberOfRegistrationsAvailable;
        this.description = description;
        this.image = image;
        this.eventAttendees = listOfAttendees;
    }

    // Because event doesnt have a unique attribute like email, special case must be applied to give events some primary key/identifier
    // Incase an event is created, stored and then must be retrieved again, this function allows it to do so
    // Query the DB to find the last autoincremented key and assign that to event
    // Event ID now alligns with the events ID when autoincremnted and stored in the DB
    private void getValidEventIDFromDB() throws SQLException {
        try {
            // Establishing a connection to the database
            connection = DatabaseConnection.getInstance();

            // Creating a statement object to execute SQL queries
            Statement statement = connection.createStatement();

            // Executing the query to get the last auto-increment value
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GroupUpEvents");
            int lastEventID = 0;
            // Retrieving the result
            while (resultSet.next()) {
                // Get to the last row, get the eventID and add 1 to get the next available eventID.
                lastEventID = resultSet.getInt("eventID");
            }

            eventID = lastEventID + 1;

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getEventID() {
        return eventID;
    }


    public int getEventCreatorUserID() {
        return eventCreatorUserID;
    }

    public String getEventAttendees(){
        return eventAttendees;
    }

    public void setEventAttendees(String userEmailToAdd){
        // Prevents unecessary comma at start of the attribute by checking if field null/not assigned value
        if (eventAttendees == null){
            eventAttendees = eventAttendees + userEmailToAdd;
            return;
        }
        eventAttendees += eventAttendees + "," + userEmailToAdd;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getGenre() {
        return genre;
    }

    public String getNumberOfRegistrationsAvailable() {
        return numberOfRegistrationsAvailable;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    // Setters
    public void setEventID(int ID){
        eventID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumberOfRegistrationsAvailable(String numberOfRegistrationsAvailable) {
        this.numberOfRegistrationsAvailable = numberOfRegistrationsAvailable;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getAllEventDetails() {
        return "Event ID: " + eventID +
                ", Event Creator UserID: " + eventCreatorUserID +
                ", Name: " + name +
                ", Date: " + date +
                ", Time: " + time +
                ", Location: " + location +
                ", Genre: " + genre +
                ", Number of Registrations Available: " + numberOfRegistrationsAvailable +
                ", Description: " + description;
    }


}