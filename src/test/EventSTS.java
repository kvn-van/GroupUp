import com.example.groupupcab302.Event;
import com.example.groupupcab302.GroupUpUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Database must be empty for STS to work
public class EventSTS {

    private Event event;
    private GroupUpUser groupUpUser;

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
    public void testSetEventAttendees() {
        event.setEventAttendees("NEWATTENDEE, ANOTHERNEWATTENDEE");
        assertTrue(event.getEventAttendees().contains("NEWATTENDEE"));
        assertTrue(event.getEventAttendees().contains("ANOTHERNEWATTENDEE"));
    }
}
