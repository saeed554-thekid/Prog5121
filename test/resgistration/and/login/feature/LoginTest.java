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
       String expected = "Username is not correctly formatted;"
                + "please ensure that your username contains an underscore"
                + "and is more more than 5 characters in length";
       assertEquals(expected, login.registerUser("kyl_1!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome"));
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements(){
        String expected = "Password sucessfully captured";
        assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome" ));
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements(){
    String expected = "Passowrd is not correctly formatted,"
                 + "please ensure the password contains atleast 8 characters, "
                 + "a capital letter, a number and a special character";
    assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome" ));
    }
    @Test
    public void testCellphoneCorrectlyFormatted(){
        String expected = "Cellphone number successfully captured";
        assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome"));
    }
    @Test
    public void testCellphoneIncorrectlyFormatted(){
        String expected = "Cellphone number is incorrectly formatted "
                + " or does not contain an international code;"
                + " please correct the number and try again";
        assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "Danny", "Dome"));
    }
   @Test
   public void testLoginSuccessfully(){
       login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome");
       assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
   }
   @Test
   public void testLoginFailed(){
       login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome");
       assertFalse(login.loginUser("kyl_1!!!!!!", "Ch&&sec@ke99!" ));
   }
   
}

