import com.example.groupupcab302.CreateEventController;
import com.example.groupupcab302.CustomSQLException;
import com.example.groupupcab302.DatabaseConnection;
import com.example.groupupcab302.mockDAO.MockEventDAO;
import com.example.groupupcab302.Objects.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.*;

import java.time.LocalDate;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;

public class eventCreateSTS{
    private CreateEventController createEventController;

    private Connection connectionToDatabase;

    private MockEventDAO mockEventDAO;

    public eventCreateSTS(){
        connectionToDatabase = DatabaseConnection.getInstance();
        mockEventDAO = new MockEventDAO();
    }

    @BeforeEach
    public void setUp() {
        createEventController = new CreateEventController();
    }

    @Test
    public void testCreateTable(){
        assertDoesNotThrow(() -> {
            mockEventDAO.createTable();
        }, "Table creation should not throw an exception");
        try{
            DatabaseMetaData dbm = connectionToDatabase.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "MockGrouUpEvents", null);
            if(tables.next()){

                tables.close();
            }
            else {
                fail("MockGroupUpEvents was not created successfully");
            }
        }

        catch (SQLException sqlException){
            fail("SQL Error");
        }

        finally{
            mockEventDAO.deleteTable();
        }
    }

    @Test
    public void testCreateEvent() throws CustomSQLException, SQLException {
        //Test Data
        String eventName = "Test Event";
        String eventDate = "12/12/2023";
        String location = "Test Location";
        String summary = "Test Summary";
        String genre = "Test Genre";
        int guestLimit = 10;
        String image = "test_image.jpg";

        //Set details
        createEventController.eventName.setText(eventName);
        createEventController.eventDate.setText(eventDate);
        createEventController.eventLocation.setText(location);
        createEventController.eventDescription.setText(summary);
        createEventController.eventGenre.setText(genre);
        createEventController.eventRegistrationQuantity.setText(String.valueOf(guestLimit));
        createEventController.selectedFile = new File(image);

        Event testEvent = new Event(1, eventName, eventDate, 0, location, genre, guestLimit, summary, image);
        when(mockEventDAO.insert(testEvent)).then(1);

        createEventController.createEvent();

        Event actualEvent = mockEventDAO.getEventById(1);
        assertEquals(testEvent, actualEvent);

    }
}
