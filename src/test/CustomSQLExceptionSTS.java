
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Basic unit test for ensuring the message of an error matches the reason supplied during construction
public class CustomSQLExceptionSTS {
    @Test
    public void testCustomSQLException() {
        // Create an sql error with custom message
        String reason = "Test error reason :3";

        //Throw custom sql error on purpose
        CustomSQLException exception = new CustomSQLException(reason);

        //Get the error, extract message and compare to original custom message
        assertEquals(reason, exception.getMessage());
    }
}
