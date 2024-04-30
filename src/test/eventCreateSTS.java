import com.example.groupupcab302.CreateEventController;
import org.junit.jupiter.api.BeforeEach;
import javafx.scene.control.DatePicker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.*;

import java.time.LocalDate;

public class CreateEventControllerText{
    private CreateEventController createEventController;

    private MockEventDAO mockEventDAO

    @BeforeEach
    public void setUp() {
        createEventController = new CreatEventController();
        createEventController.EventDA = mockEventDAO;
    }

    @Test
    public void testCreateEvent() throws CustomSQLException {
        //Test Data
        String eventName = "Test Event";
        LocalDate eventDate = LocalDate.now();
        String location = "Test Location";
        String summary = "Test Summary";
        String genre = "Test Genre";
        int guestLimit = 10;
        String image = "test_image.jpg";

        //Set details
        createEventController.NAMETEXT.setText(eventName);
        createEventController.DATETEXT.setValue(eventDate);
        createEventController.LOCATIONTEXT.setText(location);
        createEventController.EVENTTEXT.setText(summary);
        createEventController.GENRETEXT.setText(genre);
        createEventController.GUESTLIMIT.setText(String.ValueOf(guestLimit));
        createEventController.selectedFile = new File(image);

        Event testEvent = new Event(1, eventName, eventDate, 0, location, genre, guestLimit, summary, image);
        when(mockEventDAO.insert(expectedEvent)).thenReturn(1);

        createEventController.createEvent();

        Event actualEvent = mockEventDAO.getEventById(1);
        assertEquals(testEvent, actualEvent);

    }
}
