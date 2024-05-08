import com.example.groupupcab302.Event;
import com.example.groupupcab302.EventDAO;
import com.example.groupupcab302.GroupUpUser;
import com.example.groupupcab302.CustomSQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventDAOSTS {
    private EventDAO eventDAO;
    private GroupUpUser user;

    private Event event;


    @BeforeEach
    void setUp() throws SQLException {
        eventDAO = new EventDAO();
        user = new GroupUpUser(1, "username", "firstName", "lastName", "email@example.com", "123456789", "25", "password");
    }

    // Due to the nature of the tests, if delete is run work it will fail testUpdateEvent
    // This is because delete removes a row, then test update event creates another event with the key of the event that was just deleted
    // Upon insertion into DB, DB auto increments key by +1
    // This means the event objects ID doesnt align with its entry in the db which is eventID + 1
    // Run individually for testing purposes!
    @Test
    void testUpdateEvent() throws SQLException {
        // Insert an event first to have something to update
        event = new Event(user, "Test Event", "2024-05-01", "12:00 PM", "Location", "Genre", 100, "Description", "image.jpg");
        eventDAO.insert(event);

        eventDAO.update(event, "genre", "Clubbing");
        Event queriedEvent = eventDAO.getEventById(event.getEventID());
        assertEquals("Clubbing", queriedEvent.getGenre());

    }

    @Test
    void testDeleteEvent() throws SQLException {
        // Insert an event first to ensure there's something to delete
        // Call the constuctor for the event where "listofattendees" isnt required.
        // This is because when an event is created users cant pre-signup to an event so it must always be null

        // Note that when a record is deleted and another is readded, autoincrement does not set the key to the key of the deleted row
        // i.e if eventID 30 is removed then the next insertion takes 31
        // Delete is functional however its not expected for events to be deleted in this manner i.e after you insert you then constantly delete
        Event eventToDelete = new Event(user, "Test Event", "2024-05-01", "12:00 PM", "Location", "Genre", 100, "Description", "image.jpg");
        eventDAO.insert(eventToDelete);
        assertDoesNotThrow(() -> {
            eventDAO.delete(eventToDelete.getEventID());
        });

        Event event = eventDAO.getEventById(eventToDelete.getEventID());
        if (event != null) {
            fail("Event with the corresponding eventID was returned from the database instead of being deleted");
        }
    }

    //Test must be conducted on an empty events table
    @Test
    void testGetAllEvents() throws SQLException {
        try {
            GroupUpUser user1 = new GroupUpUser(1, "username1", "John", "Doe", "john@example.com", "123456789", "25", "password1");
            GroupUpUser user2 = new GroupUpUser(2, "username2", "Jane", "Smith", "jane@example.com", "987654321", "30", "password2");
            GroupUpUser user3 = new GroupUpUser(3, "username3", "Alice", "Johnson", "alice@example.com", "555555555", "22", "password3");

            // Creating multiple instances of Event
            Event event1 = new Event(user1, "Event 1", "2024-05-10", "3:00 PM", "Park", "Outdoor", 50, "Family picnic", "picnic.jpg");
            Event event2 = new Event(user2, "Event 2", "2024-06-15", "7:00 PM", "Concert Hall", "Music", 200, "Live concert", "concert.jpg");
            Event event3 = new Event(user3, "Event 3", "2024-07-20", "6:30 PM", "Museum", "Art", 80, "Art exhibition", "art.jpg");

            eventDAO.insert(event1);
            eventDAO.insert(event2);
            eventDAO.insert(event3);

            List<Event> eventsFromDB = eventDAO.getAllEvents();

            int loopCounter = 0;
            GroupUpUser[] users = {user1, user2, user3};

            for (Event events : eventsFromDB){
                assertEquals(eventsFromDB.get(loopCounter).getEventCreatorUserID(), events.getEventCreatorUserID());
                assertEquals(eventsFromDB.get(loopCounter).getName(), events.getName());
                assertEquals(eventsFromDB.get(loopCounter).getDate(), events.getDate());
                assertEquals(eventsFromDB.get(loopCounter).getTime(), events.getTime());
                assertEquals(eventsFromDB.get(loopCounter).getLocation(), events.getLocation());
                assertEquals(eventsFromDB.get(loopCounter).getGenre(), events.getGenre());
                assertEquals(eventsFromDB.get(loopCounter).getNumberOfRegistrationsAvailable(), events.getNumberOfRegistrationsAvailable());
                assertEquals(eventsFromDB.get(loopCounter).getDescription(), events.getDescription());
                assertEquals(eventsFromDB.get(loopCounter).getImage(), events.getImage());
                loopCounter += 1;
            }
        } catch (SQLException sqlException) {
            fail("The test case failed due to the sql error" + sqlException);
        }


        //Some methods are missing/not tested because they were temporarily used to devise a solution but will be replaced appropriately!
    }
}