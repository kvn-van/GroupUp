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
         assertEquals("Please ensure entries for age and phone number are fulfilled with only integers and that phone number starts with a leading 0", ErrorConstants.INT_PARSE_ERROR.getErrorDescription());
         assertEquals("You must be 18 years or older to use this site!", ErrorConstants.INVALID_AGE.getErrorDescription());
         assertEquals("Your phone number must be 10 digits long! Do not include spaces, + or -", ErrorConstants.INVALID_PHONE_NUMBER.getErrorDescription());
         assertEquals("The passwords you entered either do not match or your passwords do not have at least: 1 upper case letter, 1 lowercase, 1 special character (i.e $,. ^),  1 number or are not 8 characters long", ErrorConstants.INVALID_PASSWORD.getErrorDescription());
         assertEquals("One or more input fields have not been filled out!", ErrorConstants.INVALID_USERINPUT.getErrorDescription());
         assertEquals("Email entered is invalid. Please make sure it has a @ in the input!", ErrorConstants.INVALID_EMAIL.getErrorDescription());
         assertEquals("Email entered for signup is already registered to a user. Please consider logging in under this email!", ErrorConstants.EMAIL_IN_USE.getErrorDescription());
         assertEquals("Error occurred when trying to register you to the database. Please try again and make sure a database has been initialized", ErrorConstants.INSERTING_USER_ERROR.getErrorDescription());
     }

     @Test
     public void testRetrieveErrorConstantDescription() {
         assertEquals("You must be 18 years or older to use this site!", ErrorConstants.retrieveErrorConstantDescription("-2"));
         assertEquals("Email entered for signup is already registered to a user. Please consider logging in under this email!", ErrorConstants.retrieveErrorConstantDescription("-7"));
         assertNull(ErrorConstants.retrieveErrorConstantDescription("-100")); // Error value not found
     }
}
