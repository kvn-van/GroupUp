package com.example.groupupcab302;

import java.sql.*;
import java.time.LocalDate;


// Implement the database interface with the datatype of the class event as the parameter
// Allows overriding of interface methods and for specific operations on the user objects and database
// Defines the generic for the interface to be event objects
public class EventDAO implements IDatabaseDAO<Event>{

    private Connection connectionToDatabase;
    public EventDAO(){
        connectionToDatabase = DatabaseConnection.getInstance();
    }

    @Override
    public void createTable(){
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS GroupUpEvents ("
                            + "eventID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR NOT NULL, "
                            + "date DATETIME NOT NULL, "
                            + "location VARCHAR NOT NULL, "
                            + "genre VARCHAR NOT NULL, "
                            + "numberOfRegistrationsAvailable INT NOT NULL, "
                            + "descriptionOfEvent VARCHAR NOT NULL, "
                            + "image VARBINARY NOT NULL, "
                            + "customerEventCreationID INT NOT NULL UNIQUE, "
                            + "FOREIGN KEY (customerEventCreationID) REFERENCES GroupUpUsers(userID)"
                            + ")"
            );
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }

    @Override
    public void insert(Event event) throws CustomSQLException{
        try {
            PreparedStatement insertEvent = connectionToDatabase.prepareStatement("INSERT INTO GroupUpEvents (eventCreatoruserID, name, " +
                    "date, location, genre, numberOfRegistrationsAvailable, descriptionOfEvent, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertEvent.setInt(1, event.getEventCreatorUserID());
            insertEvent.setString(2, event.getName());
            insertEvent.setDate(3, Date.valueOf(event.getDate()));
            insertEvent.setString(4, event.getLocation());
            insertEvent.setString(5, event.getGenre());
            insertEvent.setInt(6, event.getNumberOfRegistrationsAvailable());
            insertEvent.setString(7, event.getDescription());
            insertEvent.setInt(8, event.getImage());

            insertEvent.execute();
        }
        catch(SQLException sqlException){
            System.out.println(sqlException);
            System.out.println("Error found please see above ^");
            System.out.println("Any errors here should be created as classes for the custom SQLException and given a meaningful message.");
        }
    }

    @Override
    public void delete(int eventID) throws CustomSQLException{
        try{
            PreparedStatement deleteEvent = connectionToDatabase.prepareStatement("DELETE * FROM GroupUpEvents WHERE eventID = ?");
            deleteEvent.setInt(1, eventID);
        }

        catch(SQLException sqlException){
            throw new CustomSQLException("Please enter a reason/give a meaningful message to the user on the error for why the db couldnt be deleted");
        }

    }

    // update not defined in interface as not all child tables require
    public void update(Event event, String attributeOfEventToUpate, String valueToSetAttributeTo) throws CustomSQLException{
        try{
            PreparedStatement updateToRowStatement = connectionToDatabase.prepareStatement("UPDATE GroupUpEvents SET ? = ? WHERE eventID = ?");
            updateToRowStatement.setString(1, attributeOfEventToUpate);
            updateToRowStatement.setString(2, valueToSetAttributeTo);
            updateToRowStatement.setInt(3,event.getEventID());

        }

        catch (SQLException sqlException){
            throw new CustomSQLException("Enter a detailed message here for reason of error and what user did wrong");
        }

    }



}
