package resgistration.and.login.feature;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayTest {
    private Login makeStore(){
       Login store = new Login();
       store.addMessage("0000000001", "00:0:DIDCAKE", "+27834557896", "Did you get the cake?", "Sent");
       store.addMessage("0000000002", "00:1:WHERETIME", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
       store.addMessage("0000000003", "00:2:YOOOOGATE", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
       store.addMessage("0838884567", "08:3:ITIME", "0838884567", "It is dinner time!", "Sent");
       store.addMessage("0000000005", "00:4:OKYOU", "+27838884567", "Ok, I am leaving without you.", "Stored");
       return store;
       
    }
    @Test
    public void testSentMessageArrayPopulated(){
        Login store = makeStore();
        assertEquals("Did you get the cake?", store.sentMessages[0]);
        assertEquals("It is dinner time!", store.sentMessages[1]);
    }
    @Test
    public void testSearchMessageID(){
        Login store = makeStore();
        int position = store.searchMessageID("0838884567");
        assertEquals("It is dinner time!", store.messages[position]);
    }
    @Test public void testDisplayLongestMessage(){
        Login store = makeStore();
        assertEquals("Where are you? You are late! I have asked you to be on time.", store.displayLongestMessage());
          
    }
    @Test public void testSearchRecipient(){
        Login store = makeStore();
        String expected = "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.\n";
        assertEquals(expected, store.searchRecipient("+27838884567"));
    }
    
    @Test
    public void testDeleteByHash(){
        Login store = makeStore();
        String expected = "Message \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        assertEquals(expected, store.deleteByHash("00:1:WHERETIME"));
        
    }
    @Test
    public void testDisplayReport(){
        Login store = makeStore();
        String report = store.displayReport();
        assertTrue(report.contains("Message Hash: 00:0:DIDCAKE\nRecipient: +27834557896\nMessage: Did you get the cake?"));
        assertTrue(report.contains("Message Hash: 08:3:ITIME\nRecipient: 0838884567\nMessage: It is dinner time!"));
        
    }
}
