

import com.example.groupupcab302.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class databaseConnectionSTS {

    private Connection databaseConnection;

    @BeforeEach
    void setup(){
        databaseConnection = DatabaseConnection.getInstance();
    }

    @Test
    void testGetInstance() {
        Connection conn1 = DatabaseConnection.getInstance();
        Connection conn2 = DatabaseConnection.getInstance();

        // Ensure that the same instance is returned each time getInstance() is called
        assertSame(conn1, conn2);
    }

    @Test
    void testConnectionNotNull() {
        Connection conn = DatabaseConnection.getInstance();

        // Ensure that the connection object is not null
        assertNotNull(conn);
    }

    @Test
    void testConnectionIsOpen() {
        Connection conn = DatabaseConnection.getInstance();
        boolean isOpen = false;

        try {
            isOpen = !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Ensure that the connection is open
        assertTrue(isOpen);
    }
    
}
