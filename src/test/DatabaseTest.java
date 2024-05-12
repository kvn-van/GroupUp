import com.example.addressbook.model.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Connection conn = null;

    @BeforeEach
    public void setUp() {
        conn = SqliteConnection.getInstance();
        assertNotNull(conn, "Connection should not be null");
    }

    @Test
    public void testActiveConnection() {
        try (Statement stmt = conn.createStatement()) {
            assertTrue(stmt.execute("SELECT 1"), "Connection should be able to execute a simple query");
        } catch (SQLException e) {
            fail("SQL exception occurred during test execution: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionProperties() throws SQLException {
        assertTrue(conn.getAutoCommit(), "Connection should be in auto-commit mode by default");
        assertFalse(conn.isClosed(), "Connection should be open");
    }

    @AfterEach
    public void tearDown() {
        if (conn != null) {
            try {
                conn.close();
                assertTrue(conn.isClosed(), "Connection should be closed after test");
            } catch (SQLException e) {
                fail("SQL exception occurred during closing connection: " + e.getMessage());
            }
        }
    }
}
