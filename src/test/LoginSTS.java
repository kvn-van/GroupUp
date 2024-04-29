import com.example.groupupcab302.Event;
import com.example.groupupcab302.GroupUpUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSTS {

    private GroupUpUser groupUpUser;

    @BeforeEach
    void setup() {
        groupUpUser = new GroupUpUser(1,"FREESHEFFG", "Sheff", "G", "FlowsPart2@gmail.com", "1234567891", "18", "freeSHEFFGAND8THBLOCK");
    }

    @Test
    public void testGetUserEmail() {
        assertEquals("FlowsPart2@gmail.com", groupUpUser.getEmail());
    }

    @Test
    public void testGetUserPassword() {
        assertEquals("1234567891", groupUpUser.getPassword());
    }

    @Test
    public void testDoesPasswordMatch() {
        assertEquals("1234567891", groupUpUser.getPassword());
    }

    @Test
    public void testAreTextFieldsValid() {
        assertEquals("1234567891", groupUpUser.getPassword());
    }

    @Test
    public void testValidLogin() {
        assertEquals("You have successfully logged in!", groupUpUser.LoginStatus());
    }

}