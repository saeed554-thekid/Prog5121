package resgistration.and.login.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

public class LoginTest {
    Login login = new Login();
    
    public LoginTest(){
        
    }

    @Test
    public void testUsernameCorrectlyFormatted(){
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome");
        String expected = "Welcome Danny, Dome it is great to see you again.";
        assertEquals(expected, login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted(){
       String expected = "Username is incorrectly formatted;"
               + " pleasure ensure that your username contains an underscore and "
               + " is no more than 5 characters in length";
       assertEquals(expected, login.registerUser("kyl_1!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome"));
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements(){
        String expected = "Password sucessfully captured";
        assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome" ));
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements(){
    String expected = "Password is not correctly formatted;"
            + " please ensure that the password contains at least 8 characters, a capital letter"
            + " a number and a special character";
    assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome" ));
    }
}

