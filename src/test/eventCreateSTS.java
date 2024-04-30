import com.example.groupupcab302.CreateEventController;
import com.example.groupupcab302.CustomSQLException;
import com.example.groupupcab302.DatabaseConnection;
import com.example.groupupcab302.mockDAO.MockEventDAO;
import com.example.groupupcab302.Event;
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
        createEventController.MockEventDA= mockEventDAO;
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
        createEventController.GUESTLIMIT.setText(String.valueOf(guestLimit));
        createEventController.selectedFile = new File(image);

        Event testEvent = new Event(1, eventName, eventDate, 0, location, genre, guestLimit, summary, image);
        when(mockEventDAO.insert(testEvent)).then(1);

        createEventController.createEvent();

        Event actualEvent = mockEventDAO.getEventById(1);
        assertEquals(testEvent, actualEvent);

    }
}
