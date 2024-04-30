package com.example.groupupcab302.mockDAO;

import com.example.groupupcab302.DatabaseConnection;
import com.example.groupupcab302.Event;
import javafx.beans.value.ObservableBooleanValue;

import java.sql.*;

public class MockEventDAO {
    private Connection connectionToDatabase;

    public MockEventDAO () {connectionToDatabase = DatabaseConnection.getInstance();}

    public void deleteTable(){
        try {
            PreparedStatement deleteTableStatement = connectionToDatabase.prepareStatement("DROP TABLE IF EXISTS MockGroupUpEvents");
            deleteTableStatement.execute();
        }

        catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void createTable(){
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS MockGroupUpEvents("
                        + "eventID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "name VARCHAR NOT NULL, "
                        + "date DATETIME NOT NULL, "
                        + "location VARCHAR NOT NULL, "
                        + "genre VARCHAR NOT NULL, "
                        + "numberOfRegistrationsAvailable INT NOT NULL, "
                        + "descriptionOfEvent VARCHAR NOT NULL, "
                        + "image VARBINARY NOT NULL, "
                            // need clarification + "listOfAttendees STRING UNIQUE NULL, "
                        + "customerEventCreationID INT NOT NULL UNIQUE, "
                        + "FOREIGN KEY (customerEventCreationID) REFERENCES GroupUpUsers(userID)"
                        + ")"

            );
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }

    public Event getEventById(int eventID) throws SQLException {
        Event event = null;
        try{
            PreparedStatement selectEventStatement = connectionToDatabase.prepareStatement(
                    "SELECT * FROM MockGroupUpEvents WHERE eventID = ?"
            );

            selectEventStatement.setInt(1,eventID);
            ResultSet resultSet = selectEventStatement.executeQuery();

            if (resultSet.next()) {
                event = new Event(
                        resultSet.getInt("groupUpUser"),
                        resultSet.getString("name"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getInt("time"),
                        resultSet.getString("location"),
                        resultSet.getString("genre"),
                        resultSet.getInt("numberOfRegistrationsAvailable"),
                        resultSet.getString("descriptionOfEvent"),
                        resultSet.getString("image")
                );
            }
        }

        catch (SQLException sqlEX) {
            System.out.println(sqlEX);
        }

        return event;
    }
    public ObservableBooleanValue insert (Event event){
        try {
            PreparedStatement insertEvent = connectionToDatabase.prepareStatement(
                    "INSERT INTO MockGroupUpEvents (EventCreatorUserID, name, date, location, genre, numberOfRegistrationsAvailable, descriptionOfEvent, image) VALUES (?,?,?,?,?,?,?,?)"
            );

            insertEvent.setInt(1, event.getEventCreatorUserID());
            insertEvent.setString(2, event.getName());
            insertEvent.setDate(3, Date.valueOf(event.getDate()));
            insertEvent.setString(4, event.getLocation());
            insertEvent.setString(5, event.getGenre());
            insertEvent.setInt(6, event.getNumberOfRegistrationsAvailable());
            insertEvent.setString(7, event.getDescription());
            insertEvent.setString(8, event.getImage());
        }

        catch (SQLException exception) {
            System.out.println(exception);
        }
        return null;
    }
}
