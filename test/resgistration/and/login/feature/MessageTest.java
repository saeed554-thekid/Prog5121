package resgistration.and.login.feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class MessageTest {
    Login login = new Login();
    
    public MessageTest() {
    }
   
    @Test
    public void testMessageLengthSuccess(){
        String expected = "Message ready to send.";
        assertEquals(expected, login.checkMessage("Hi Mike, can you join us for dinner tonight?"));
    }
  
    @Test
    public void testMessageLengthFailure(){
        String longMessage = "";
        for(int i = 0; i < 260; i++){
            longMessage = longMessage + "a";
        }
        String expected = "Message exceeds 250 characters by 15; please reduce the size.";        
    }
    
    @Test
    public void testRecipientCorrectlyFormatted(){
        String expected = "Cellphone number successfully captured.";
        assertEquals(expected, login.checkRecipientCell("+2771869002"));
    }
    
    @Test
    public void testRecipientIncorrectlyFormatted(){
        String expected = "Cellphone number is incorrectly formatted"
                + " or does not contain an international code."
                + " Please coorect the number and try again";
    }
    
}
