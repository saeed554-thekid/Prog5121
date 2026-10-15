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
        String expected = "Message exceeds 250 characters by 10; please reduce the size."; 
        assertEquals(expected, login.checkMessage(longMessage));
    }
    
    @Test
    public void testRecipientCorrectlyFormatted(){
        String expected = "Cellphone number successfully captured.";
        assertEquals(expected, login.checkRecipientCell("+27718693002"));
    }
    
    @Test
    public void testRecipientIncorrectlyFormatted(){
        String expected = "Cellphone number is incorrectly formatted"
                + " or does not contain an international code."
                + " Please correct the number and try again.";
        assertEquals(expected, login.checkRecipientCell("08575975889"));
    }
    
    @Test
    public void testMessageHashTestCase1(){
        login.setMessageID("0012345678");
        login.setNumMessageSent(0);
        login.checkMessage("Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", login.createMessageHash());
    }
    
    @Test
    public void testMessageHashesInAloop(){
      String[] ids = {"0012345678", "1234567890"};
      String[] texts = {"Hi Mike, can you join us for dinner tonight?", "Hi Keegan did you recieve the payment?"};
      String[] expectedHashes = {"00:0:HITONIGHT", "12:1:HIPAYMENT"};
      
      for(int i = 0; i< ids.length; i++){
          Login m = new Login();
          m.setMessageID(ids[i]);
          m.setNumMessageSent(i);
          m.checkMessage(texts[i]);
         assertEquals(expectedHashes[i], m.createMessageHash());
      }
    }
    
    @Test
    public void testMessageIDCreated(){
        String result = login.createMessageID();
        assertTrue(result.startsWith("Message ID generated"));
    }
    
    @Test
    public void testSendMessage(){
        login.createMessageID();
        login.checkRecipientCell("+27718693002");
        login.checkMessage("Hi Mike, can you join us for dinner tonight");
        login.createMessageHash();
        assertEquals("Message successfully sent.", login.SentMessage("Send Message"));
    }
    
    @Test
    public void testDisregardMessage(){
        assertEquals("Press 0 to delete the message.", login.SentMessage("Disregard Message"));        
    }
    
    @Test
    public void testStoreMessage(){
        login.createMessageID();
        login.checkRecipientCell("+27718693002");
        login.checkMessage("Hi Mike, can you join us for dinner tonight");
        login.createMessageHash();
        assertEquals("Message successfully stored.", login.SentMessage("Store Message to send later"));
    }
    
    @Test
    public void testTotalMessagesSent(){
        login.createMessageID();
        login.checkRecipientCell("+27718693002");
        login.checkMessage("Hi Mike, can you join us for dinner tonight?");
        login.createMessageHash();
        login.SentMessage("Send Message");
        
        login.createMessageID();
        login.checkMessage("Hi Keegan, did you recieve the payment?");
        login.createMessageHash();
        login.SentMessage("Disregard Message");
        assertEquals(1, login.returnTotalMessages());
     
    }
}
