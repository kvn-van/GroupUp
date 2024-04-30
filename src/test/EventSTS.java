import com.example.groupupcab302.Event;
import com.example.groupupcab302.GroupUpUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Database must be empty for STS to work
public class EventSTS {

    private Event event;
    private GroupUpUser groupUpUser;
    // Todo: After disucssing and figuring out how date,  time and image needs to work add STS cases
    @BeforeEach
    void setUp(){
        // Define a fixed userID for both user and event to prevent it from changing with varying test cases
        groupUpUser = new GroupUpUser(1, "FREESHEFFG", "Sheff", "G", "FlowsPart2@gmail.com", "1234567891", "18", "freeSHEFFGAND8THBLOCK");
        event = new Event(1, groupUpUser.getUserID(), "10EleventConcert", "2024-05-01", "12:00 PM", "Uk Greensborough", "Concert", 1000, "FREE THE GUYS, FREE DIGGA AND FREE JSAV", "image.jpg", "JSAV, FISH");
    }

    @Test
    public void testGetEventID(){
        assertEquals(1, event.getEventID());
    }


    @Test
    public void testGetEventAttendees() {
        assertTrue(event.getEventAttendees().contains("JSAV"));
        assertTrue(event.getEventAttendees().contains("FISH"));
    }

    @Test
    public void testGetEventCreatorUserID() {
        assertEquals(groupUpUser.getUserID(), event.getEventCreatorUserID());
    }

    @Test
    public void testGetName() {
        assertEquals("10EleventConcert", event.getName());
    }

    @Test
    public void testGetDate() {
        assertEquals("2024-05-01", event.getDate());
    }

    @Test
    public void testGetTime() {
        assertEquals("12:00 PM", event.getTime());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Uk Greensborough", event.getLocation());
    }

    @Test
    public void testGetGenre() {
        assertEquals("Concert", event.getGenre());
    }

    @Test
    public void testGetNumberOfRegistrationsAvailable() {
        assertEquals(1000, event.getNumberOfRegistrationsAvailable());
    }

    @Test
    public void testGetDescription() {
        assertEquals("FREE THE GUYS, FREE DIGGA AND FREE JSAV", event.getDescription());
    }

    @Test
    public void testGetImage() {
        assertEquals("image.jpg", event.getImage());
    }

    @Test
    public void testSetEventID() {
        event.setEventID(5);
        assertEquals(5, event.getEventID());
    }

    @Test
    public void testSetDate() {
        event.setDate("2024-04-21");
        assertEquals("2024-04-21", event.getDate());
    }

    @Test
    public void testSetTime() {
        event.setTime("12:00 PM");
        assertEquals("12:00 PM", event.getTime());
    }

    @Test
    public void testSetLocation() {
        event.setLocation("New Location");
        assertEquals("New Location", event.getLocation());
    }

    @Test
    public void testSetGenre() {
        event.setGenre("New Genre");
        assertEquals("New Genre", event.getGenre());
    }

    @Test
    public void testSetNumberOfRegistrationsAvailable() {
        event.setNumberOfRegistrationsAvailable(500);
        assertEquals(500, event.getNumberOfRegistrationsAvailable());
    }

    @Test
    public void testSetDescription() {
        event.setDescription("New Description");
        assertEquals("New Description", event.getDescription());
    }

    @Test
    public void testSetImage() {
        event.setImage("New Image");
        assertEquals("New Image", event.getImage());
    }

    @Test
    public void testGetAllEventDetails() {
        // Define the expected result
        String expectedDetails = "Event ID: 1, Event Creator UserID: 1, Name: 10EleventConcert, Date: ToBeFinalized, Time: ToBeFinalized, Location: Uk Greensborough, Genre: Concert, Number of Registrations Available: 1000, Description: FREE THE GUYS, FREE DIGGA AND FREE JSAV";

        // Call the getAllEventDetails() method
        String actualDetails = event.getAllEventDetails();

        // Check if the actual details match the expected details
        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    public void testSetEventAttendees() {
        event.setEventAttendees("NEWATTENDEE, ANOTHERNEWATTENDEE");
        assertTrue(event.getEventAttendees().contains("NEWATTENDEE"));
        assertTrue(event.getEventAttendees().contains("ANOTHERNEWATTENDEE"));
    }

    // To test this you must ensure DB is manually opened and compare the printed value. if printed value is +1 to that of last events ID in db table then it works
    @Test
    public void testingAutoIncrementingOfEventID() throws SQLException {
        GroupUpUser user = new GroupUpUser("user1", "John", "Doe", "john@example.com", "123456789", "25", "password1");
        Event event = new Event(user, "Sample Event", "2024-05-01", "10:00 AM", "Sample Location", "Sample Genre", 100, "Sample Description", "sample.jpg");

    }
}
