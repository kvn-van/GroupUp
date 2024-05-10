import com.example.groupupcab302.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

// STS was created after prototyping the implementation to identify the general constraints/errors which the user may encounter
// Only extends to Sign up page as of now
 class ErrorConstantsSTS {

     @Test
     public void testGetErrorValue() {
         assertEquals("-1", ErrorConstants.INT_PARSE_ERROR.getErrorValue());
         assertEquals("-2", ErrorConstants.INVALID_AGE.getErrorValue());
         assertEquals("-3", ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue());
         assertEquals("-4", ErrorConstants.INVALID_PASSWORD.getErrorValue());
         assertEquals("-5", ErrorConstants.INVALID_USERINPUT.getErrorValue());
         assertEquals("-6", ErrorConstants.INVALID_EMAIL.getErrorValue());
         assertEquals("-7", ErrorConstants.EMAIL_IN_USE.getErrorValue());
         assertEquals("-8", ErrorConstants.INSERTING_USER_ERROR.getErrorValue());
     }

    @Test
    public void testGetErrorDescription() {
        assertEquals("Please use only integers for age and phone number, with no spaces \n" +
                "or special characters. Phone number should start with 0", ErrorConstants.INT_PARSE_ERROR.getErrorDescription());
        assertEquals("You must be 18 or older to sign up", ErrorConstants.INVALID_AGE.getErrorDescription());
        assertEquals("Phone number must be 10 digits long, without spaces, +, or -", ErrorConstants.INVALID_PHONE_NUMBER.getErrorDescription());
        assertEquals("Passwords must match and contain: 1 uppercase letter, \n1 lowercase letter, 1 special character ($, ., ^), 1 number,\n and be at least 8 characters long", ErrorConstants.INVALID_PASSWORD.getErrorDescription());
        assertEquals("One or more fields are empty. Please fill out all fields", ErrorConstants.INVALID_USERINPUT.getErrorDescription());
        assertEquals("Please enter a valid email address containing '@'", ErrorConstants.INVALID_EMAIL.getErrorDescription());
        assertEquals("Email is already registered. Please log in", ErrorConstants.EMAIL_IN_USE.getErrorDescription());
        assertEquals("Error registering user. Please try again", ErrorConstants.INSERTING_USER_ERROR.getErrorDescription());
    }

    @Test
    public void testRetrieveErrorConstantDescription() {
        assertEquals("You must be 18 or older to sign up", ErrorConstants.retrieveErrorConstantDescription("-2"));
        assertEquals("Email is already registered. Please log in", ErrorConstants.retrieveErrorConstantDescription("-7"));
        assertNull(ErrorConstants.retrieveErrorConstantDescription("-100")); // Error value not found
    }
}
