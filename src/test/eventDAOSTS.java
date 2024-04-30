import com.example.groupupcab302.EventDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.*;
public class eventDAOSTS {
    private EventDAO eventDAO;

    @BeforeEach
    public void setUp() {
        eventDAO = new EventDAO();
        eventDAO.createTable();
    }

    @Test
    public void testDisplayEventName() throws SQLException {
        String eventName = eventDAO.getEventNameById(1);
        assertEquals("Birthday Party", eventName);

        String eventName2 = eventDAO.getEventNameById(2);
        assertEquals("Study Group", eventName2);

        String eventName3 = eventDAO.getEventNameById(3);
        assertEquals("Group Fitness", eventName3);

        String eventName4 = eventDAO.getEventNameById(4);
        assertEquals("BBQ Party", eventName4);
    }

    @Test
    public void testDisplayDate() throws SQLException {
        String eventDate = eventDAO.getDateById(1);
        assertEquals("28/05/2024", eventDate);

        String eventDate2 = eventDAO.getDateById(2);
        assertEquals("30/04/2024", eventDate2);

        String eventDate3 = eventDAO.getDateById(3);
        assertEquals("10/05/2024", eventDate3);

        String eventDate4 = eventDAO.getDateById(4);
        assertEquals("01/06/2024", eventDate4);
    }

    @Test
    public void testDisplayLocation() throws SQLException {
        String eventLocation = eventDAO.getLocationById(1);
        assertEquals("Brisbane", eventLocation);

        String eventLocation2 = eventDAO.getLocationById(2);
        assertEquals("QUT", eventLocation2);

        String eventLocation3 = eventDAO.getLocationById(3);
        assertEquals("Fortitude Valley", eventLocation3);

        String eventLocation4 = eventDAO.getLocationById(4);
        assertEquals("Maru Grillhouse", eventLocation4);
    }

    @Test
    public void testDisplayGenre() throws SQLException {
        String eventGenre = eventDAO.getGenreById(1);
        assertEquals("Party", eventGenre);

        String eventGenre2 = eventDAO.getGenreById(2);
        assertEquals("productivity", eventGenre2);

        String eventGenre3 = eventDAO.getGenreById(3);
        assertEquals("health", eventGenre3);

        String eventGenre4 = eventDAO.getGenreById(4);
        assertEquals("dine", eventGenre4);
    }
}
