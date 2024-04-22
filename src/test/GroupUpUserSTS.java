import com.example.groupupcab302.GroupUpUser;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.assertEquals;// before running STS ensure the database is deleted to reset user counter otherwise first condition will failpublic class GroupUpUserSTS {    private GroupUpUser groupUpUser;    // Phone number should not have a leading 0 since number is treated as base 8 leading to errors    // In normal operations, phone number is recieved as a literal string and isnt an issue    @BeforeEach    void setUp(){        // Define a fixed userID to prevent it from changing with varying test cases        groupUpUser =  new GroupUpUser(1,"FREESHEFFG", "Sheff", "G", "FlowsPart2@gmail.com", 1234567891, 18, "freeSHEFFGAND8THBLOCK");    }    @Test    public void testGetUserID(){        assertEquals(1, groupUpUser.getUserID());    }    @Test    public void testGetUserName(){        assertEquals("FREESHEFFG", groupUpUser.getUserName());    }    @Test    public void testGetFirstName(){        assertEquals("Sheff", groupUpUser.getFirstName());    }    @Test    public void testGetLastName(){        assertEquals("G", groupUpUser.getLastName());    }    @Test    public void testGetEmail(){        assertEquals("FlowsPart2@gmail.com", groupUpUser.getEmail());    }    @Test    public void testGetPhoneNumber(){        assertEquals(1234567891, groupUpUser.getPhoneNumber());    }    @Test    public void testGetAge(){        assertEquals(18, groupUpUser.getAge());    }    @Test    public void testGetPassword(){        assertEquals("freeSHEFFGAND8THBLOCK", groupUpUser.getPassword());    }    @Test    public void testSetUserID(){        groupUpUser.setUserID(15);        assertEquals(15, groupUpUser.getUserID());    }    @Test    public void testSetUserName(){        groupUpUser.setUserName("IHEART8BLOCK");        assertEquals("IHEART8BLOCK", groupUpUser.getUserName());    }    @Test    public void testSetFirstName(){        groupUpUser.setFirstName("V3RYRAR3BOYZ");        assertEquals("V3RYRAR3BOYZ", groupUpUser.getFirstName());    }    @Test    public void testSetLastName(){        groupUpUser.setLastName("WE<3Tecca");        assertEquals("WE<3Tecca", groupUpUser.getLastName());    }    @Test    public void testSetEmail(){        groupUpUser.setEmail("SAYMYGRACE@gmail.com");        assertEquals("SAYMYGRACE@gmail.com", groupUpUser.getEmail());    }    @Test    public void testSetPhoneNumber(){        // if this test case passes for a number without leading 0's then functionality is good        groupUpUser.setPhoneNumber(1234567892);        assertEquals(1234567892, groupUpUser.getPhoneNumber());    }    @Test    public void testSetAge(){        groupUpUser.setAge(100);        assertEquals(100, groupUpUser.getAge());    }    @Test    public void testSetPassword(){        groupUpUser.setPassword("FE!N");        assertEquals("FE!N", groupUpUser.getPassword());    }    @Test    public void testGetAllUserDetails(){        String userDetails = "userName: " + "FREESHEFFG" + ", First Name: " + "Sheff" + ", Last Name: "                + "G" + ", email: " + "FlowsPart2@gmail.com" + ", phoneNumber: " + "1234567891" + ", age: " +                18 + ", password: " + "freeSHEFFGAND8THBLOCK";        assertEquals(userDetails, groupUpUser.getAllUserDetails());    }}