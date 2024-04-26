
import com.example.groupupcab302.ErrorConstants;
import com.example.groupupcab302.GroupUpUser;
import com.example.groupupcab302.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// STS class to test new functionalities in userDAO
// does not extend basic crud operations or table creation as it already tested and developed in the mockUserDAOSTS
public class UserDAOSTS {

    // No need for connection to database since methods being tested dont require it

    // Construct group up user with fixed ID to prevent complications with ID changing due to varying test cases
    final GroupUpUser groupUpUser  =  new GroupUpUser(1,"FREESHEFFG", "Sheff", "G", "FlowsPart2@gmail.com", "1234567891", "18", "rN!1FreeQUANDORONDO$");
    private UserDAO userDAO;
    @BeforeEach
    void setUp(){
        userDAO = new UserDAO();
    }
    @Test
    public void testConvertStringToInt(){
        // Define a passing test case
        assertEquals(groupUpUser.getPhoneNumber(), userDAO.convertStringToInt(groupUpUser.getPhoneNumber()));

        // Define a test case where it clearly fails to make sure
        String phoneNumber = "123dawdwa4567891";
        // Expected for the error code -1 signifying a failed attempt at parsing INT
        assertEquals(ErrorConstants.INT_PARSE_ERROR.getErrorValue(), userDAO.convertStringToInt(phoneNumber));
    }

    @Test
    public void testIsPasswordValid(){
        // Password must have atleast one capital letter, lower, special character, number and be 8 char long at minimum!
        assertEquals(true, userDAO.isPasswordValid(groupUpUser.getPassword()));
    }

    @Test
    public void testValidatePhoneNumber(){
        // Phone number must be 10 char long and parse to an int
        assertEquals(groupUpUser.getPhoneNumber(), userDAO.validatePhoneNumber(groupUpUser.getPhoneNumber()));

    }

    @Test
    public void testValidateAge(){
        // Phone number must be 10 char long and parse to an int

        String phoneNumber = "0407623446";
        assertEquals("0407623446", userDAO.validatePhoneNumber(phoneNumber));

    }


}
