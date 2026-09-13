package resgistration.and.login.feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
       String expected = "Username is not correctly formatted; "
                + "please ensure that your username contains an underscore "
                + "and is more more than 5 characters in length.";
       assertEquals(expected, login.registerUser("kyl_1!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome"));
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements(){
        String expected = "The two conditions have been met, and user has been successfully registered ";
        assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome" ));
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements(){
    String expected = "Passowrd is not correctly formatted, "
                 + "please ensure the password contains atleast 8 characters, "
                 + "a capital letter, a number and a special character.";
    assertEquals(expected, login.registerUser("kyl_1", "password", "+27838968976", "Danny", "Dome" ));
    }
    @Test
    public void testCellphoneCorrectlyFormatted(){
        String expected = "The two conditions have been met, and user has been successfully registered ";
        assertEquals(expected, login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Danny", "Dome"));
    }
    @Test
    public void testCellphoneIncorrectlyFormatted(){
        String expected = "Cellphone number is incorrectly formatted "
                + " or does not contain international code";
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
   @Test
   public void testUsernameCorrectlyFormattedType(){
       assertTrue(login.checkUserName("kyl_1"));
   }
   @Test
   public void testUsernameIncorrectlyFormattedType(){
       assertFalse(login.checkUserName("kyl_1!!!!!!"));
   }
   @Test
   public void testPasswordMeetsComplexityRequirementsType(){
       assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
   }
   @Test
   public void testPasswordDoesNotMeetComplexityRequirementsType(){
       assertFalse(login.checkPasswordComplexity("password"));
   }
   
  @Test
  public void testCellphoneCorrectlyFormattedType(){
      assertTrue(login.checkPhoneNumber("+27838968976"));
  }
  @Test
  public void testCellphoneIncorrectlyFormattedType(){
      assertFalse(login.checkPhoneNumber("08966553"));
  }
  }

