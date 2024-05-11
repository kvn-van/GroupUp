import com.example.groupupcab302.DatabaseConnection;
import com.example.groupupcab302.Objects.Event;
import com.example.groupupcab302.Objects.GroupUpUser;
import com.example.groupupcab302.mockDAO.MockEventDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockEventDAOSTS {

    private MockEventDAO mockEventDAO;
    private Connection connectionToDatabase;
    // First, create a GroupUpUser object
    private final GroupUpUser user = new GroupUpUser("username", "firstName", "lastName", "email@example.com", "123456789", "25", "password");

    // Create an event object with a fixed id to prevent issues relating to event ID
    private final Event event = new Event(1, user.getUserID(), "Event Name", "2024-05-01", "12:00 PM", "Event Location", "Event Genre", "100", "Event Description", "image.jpg", "hoodrich@gmail.com, random@gmail.com");

    @BeforeEach
    public void setUp() {
        connectionToDatabase = DatabaseConnection.getInstance();
        mockEventDAO = new MockEventDAO();
    }

    @Test
    public void testCreateTable(){
        assertDoesNotThrow(() -> {
            mockEventDAO.createTable();
        }, "Table creation should not throw an exception");
        try{
            DatabaseMetaData dbm = connectionToDatabase.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "GroupUpEvents", null);
            if(tables.next()){
                //if tables.next is true then GroupUpEvents exists!
                // STS test case passes

                // Close the result set as it keeps a lock on the database resources accessed (MockGroupUpUsers table)
                // This prevents CRUD operations

                tables.close();
            }
            else {
                fail("MockGroupUpEvents was not created successfully");
            }
        }

        catch (SQLException sqlException){
            fail("SQL Error recieved upon trying to validate the metadata and existance of MockEvent table");
        }

        finally{
            mockEventDAO.deleteTable();
        }
    }

    // By testing if you can insert an event then you also inadvertently test if you can get an event by ID
    @Test
    public void testInsertEvent() throws CustomSQLException {
        try {
            // Initialize test case by creating table
            mockEventDAO.createTable();

            // Insert the event into the mock database
            mockEventDAO.insert(event);

            // Retrieve the inserted event from the mock database
            Event dbQueriedEvent = mockEventDAO.getEventById(event.getEventID());

            // Validate the retrieved event against the original event
            assertEquals(event.getEventID(), dbQueriedEvent.getEventID());
            assertEquals(event.getEventCreatorUserID(), dbQueriedEvent.getEventCreatorUserID());
            assertEquals(event.getName(), dbQueriedEvent.getName());
            assertEquals(event.getDate(), dbQueriedEvent.getDate());
            assertEquals(event.getTime(), dbQueriedEvent.getTime());
            assertEquals(event.getLocation(), dbQueriedEvent.getLocation());
            assertEquals(event.getGenre(), dbQueriedEvent.getGenre());
            assertEquals(event.getNumberOfRegistrationsAvailable(), dbQueriedEvent.getNumberOfRegistrationsAvailable());
            assertEquals(event.getDescription(), dbQueriedEvent.getDescription());
            assertEquals(event.getImage(), dbQueriedEvent.getImage());
            assertEquals(event.getEventAttendees(), dbQueriedEvent.getEventAttendees());
            // Add assertion for eventAttendees if needed

            //Manual checking
            System.out.println("Queried Event Information:");
            System.out.println("Event ID: " + dbQueriedEvent.getEventID());
            System.out.println("Event Creator User ID: " + dbQueriedEvent.getEventCreatorUserID());
            System.out.println("Name: " + dbQueriedEvent.getName());
            System.out.println("Date: " + dbQueriedEvent.getDate());
            System.out.println("Time: " + dbQueriedEvent.getTime());
            System.out.println("Location: " + dbQueriedEvent.getLocation());
            System.out.println("Genre: " + dbQueriedEvent.getGenre());
            System.out.println("Number of Registrations Available: " + dbQueriedEvent.getNumberOfRegistrationsAvailable());
            System.out.println("Description: " + dbQueriedEvent.getDescription());
            System.out.println("Image: " + dbQueriedEvent.getImage());
            System.out.println("Event Attendees: " + dbQueriedEvent.getEventAttendees());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            mockEventDAO.deleteTable();
        }
    }



}