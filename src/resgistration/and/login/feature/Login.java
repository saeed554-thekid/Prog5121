package resgistration.and.login.feature;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Login {
      private String username;
      private String password;
      private String firstname;
      private String lastname;
      
      //Fields for part 2
      private String messageID;
      private int numMessageSent = 0;
      private String recipient; 
      private String messageText;
      private String MessageHash; 
      private String allMessages = "";   
      private String storedMessages = "";

//Methods
    public boolean checkUserName(String username){
      return  username.contains("_") && username.length() <= 5;
    }
    public boolean checkPasswordComplexity(String password){
        return password.length() >=8  && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[^a-zA-Z0-9].*");
    }
    public boolean checkPhoneNumber(String cellphone){
        return cellphone.matches("\\+27[0-9]{1,10}");
    }
    //registration response message
    public String registerUser(String username, String password, String cellphone, String firstname, String lastname){
        if(!checkUserName(username)){
            return ("Username is not correctly formatted; "
                + "please ensure that your username contains an underscore "
                + "and is more more than 5 characters in length.");
        }
     else if(!checkPasswordComplexity(password)){
          return "Passowrd is not correctly formatted, "
                 + "please ensure the password contains atleast 8 characters, "
                 + "a capital letter, a number and a special character.";
      }
     else if(!checkPhoneNumber(cellphone)){
          return "Cellphone number is incorrectly formatted "
                 + " or does not contain international code"  ;
      }
     else{
         this.username = username;
         this.password = password;
         this.firstname = firstname;
         this.lastname = lastname;
       return "The two conditions have been met, and user has been successfully registered ";
     }
        
    } 
      public boolean loginUser(String loginUsername, String loginPassword){
          return username.equals(loginUsername) && password.equals(loginPassword);
      } 
    public String returnLoginStatus( String loginUserName, String loginPassword){
        if(loginUser(loginUserName, loginPassword)){
            return "Welcome "  + firstname +  ", "  + lastname +  " it is great to see you again.";
        }
        else{
            return "Username or password is incorrect";
        }
}
       //Part 2 methods
public String createMessageID(){
    messageID = "";
    for(int  i  = 0; i < 10; i++){
    messageID = messageID + (int)(Math.random() * 10);
}
    return "Message ID generated: " + messageID;
}
public boolean checkMessageID(){
    return messageID.matches("[0-9]{10}");
}
public String checkRecipientCell(String cellphone){
    if(checkPhoneNumber(cellphone)){
        recipient = cellphone;
        return "Cellphone number successfully captured.";
}
    else{
    return "Cellphone number is incorrectly formatted"
            + " or does not contain an international code."
            + " Please correct the number and try again.";
}    
     
 }
public String checkMessage(String text){
    if(text.length() <= 250){
        messageText = text;
        return "Message ready to send.";
    }
    else{
        return "Message exceeds 250 characters by " + (text.length() - 250) + "; please reduce the size.";
    }     
}
//Returns the message hash
public String createMessageHash(){
    String[] word =  messageText.split(" ");
    String firstWord = word[0].replaceAll("[^A-Za-z0-9]","");
    String lastWord = word[word.length - 1].replaceAll("[^A-Za-z0-9]","");
    MessageHash = (messageID.substring(0, 2) + ":" + numMessageSent + ":" + firstWord + lastWord).toUpperCase();
    return MessageHash;
}

//options to do with messages
public String SentMessage(String sendOption){
    if(sendOption.equals("Send Message")){
        allMessages = allMessages + "MessageID: " + messageID + "\nMessage Hash: " + MessageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText + "\n\n";
        numMessageSent++;
        return "Message successfully sent.";
    }
    else if(sendOption.equals("Disregard Message")){
        return "Press 0 to delete the message.";
    }
    else{
        storeMessage();
        return"Message successfully stored.";
    }
     
}
public String printMessages(){
    return allMessages;
}
public int returnTotalMessages(){
    return numMessageSent;
}
//This stores messages in the JSON file
public void storeMessage(){
    try{
     if(!storedMessages.equals("")){
         storedMessages = storedMessages + ",\n";
     }
     String  safeMessage = messageText.replace("\\", "\\\\").replace("\"", "\\\"");
     storedMessages = storedMessages + "{\"messageID\": \"" + messageID + "\", \"messageHash\": \"" + MessageHash + "\", \"recipient\": \"" + recipient + "\", \"message\": \"" + safeMessage + "\"}";
     BufferedWriter writer = new BufferedWriter(new FileWriter("storeMessage.json"));
     writer.write("[\n" + storedMessages + "\n]");
             writer.newLine();
             writer.close();
    }
    catch(IOException e){
        System.out.println("Could not write JSON file");
    }
}
public void setMessageID(String messageID){
    this.messageID = messageID;
}

//gets the message ID
public String getMessageID(){
    return messageID;
}

public void setNumMessageSent( int numMessageSent){
    this.numMessageSent = numMessageSent;
}

//Part 3 methods
String[] sentMessages = new String[100];
String[] disregardedMessages = new String[100];
String[] storedMessageList = new String[100];
String[] messageHashes = new String[100];
String[] messageIDs = new String[100];

//keeps every message at the same position
String[] recipients = new String[100];
String[] messages = new String[100];
String[] flags = new String[100];
 
int sentCount = 0;
int disregardedCount = 0;
int storedCount = 0;
int total = 0;

//allows one message to get add to the arrays
public void addMessage(String id, String hash, String recipient, String message, String flag){

    messageIDs[total] = id;
    messageHashes[total] = hash;
    recipients[total] = recipient;
    messages[total] = message;
    flags[total] = flag;
    total++;
    
    if(flag.equals("Sent")){
        sentMessages[sentCount] = message;
        sentCount++;
    }
    else if(flag.equals("Stored")){
        storedMessageList[storedCount] = message;
        storedCount++;
    }
    else{
        disregardedMessages[disregardedCount] = message;
        disregardedCount++;
    }
}

public void loadStoredMessages(String fileName){
    Path file = Paths.get(fileName);
    if(!Files.exists(file)){
        return;
    }
    try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file)));
        String line = reader.readLine();
        while(line != null){
            if(line.indexOf("messageID") != -1){
                String id = getValue(line, "messageID");
                String hash = getValue(line, "messageHash");
                String recipient = getValue(line, "recipient");
                String message = getValue(line, "message");
                addMessage(id, hash, recipient, message, "Stored");
            }
            line =  reader.readLine();
        }
        reader.close();
    }
    catch(IOException e){
        System.out.println("Could note read JSON file");
    }
}
private String getValue(String line, String key){
    String find = "\"" + key + "\": \"";
    int start = line.indexOf(find) + find.length();
    int end = line.indexOf("\"", start);
    if(key.equals("message")){
      end = line.trim().length() - 2;
      if(line.trim().endsWith(",")){
          end = end - 1;
      }
    }
    return line.substring(start, end);
}
// The sender and recipient for case a 
public String displaySenderRecipient(String sender){
    String result = "";
    for(int i = 0; i < total; i++){
        if(flags[i].equals("Sent")){
            result = result + "Sender: " + sender + "Recipient: " + recipients[i] + "\n";
            
        }
    }
    return result;
}
// case b 
public String displayLongestMessage(){
    String longest = "";
    for(int i = 0; i < total; i++){
        if(messages[i].length() > longest.length()){
            longest = messages[i];
        }
    }
    return longest;
}
//case c
public int searchMessageID(String id){
    for(int i = 0; i < total; i++){
        if(messageIDs[i].equals(id)){
            return i;
        }
    }
    return -1;
}
// case d
public String searchRecipient(String recipient){
    String result = "";
    for(int i = 0; i < total; i++){
        if(recipients[i].equals(recipient) && !flags[i].equals("Disregard")){
            result = result + messages[i] + "/n";
        }
    }
    return result;
}
//case e
public String deleteByHash(String hash){
    for(int i = 0; i < total; i++){
        if(messageHashes[i].equals(hash)){
            String deleted = messages[i];
            removeFromList(i);
            return "Message \"" + deleted + "\" successfully deleted.";
        }
}
    return "Message hash not found.";
}

//moves everything of i by 1
private void removeFromList(int i){
    String flag = flags[i];
    String text = messages[i];
    for(int j = i; j < total - 1; j++){
        messageIDs[j] = messageIDs[j + 1];
        messageHashes[j] = messageHashes[j + 1];
        recipients[j] = recipients[j + 1];
        messages[j] = messages[j + 1];
        flags[j] = flags[j +1];
    }
    total--;
    if(flag.equals("Sent")){
        sentCount = removeText(sentMessages, sentCount, text);
    }
    else if(flag.equals("Stored")){
        sentCount = removeText(storedMessageList, storedCount, text);
    }
    else{
        disregardedCount =  removeText(disregardedMessages, disregardedCount, text);
    }
}
//remove a text and reutrns the new count
private int removeText(String[] list, int count, String text){
for(int i = 0; i < count; i++){
    if(list[i].equals(text)){
        for(int j = i; j < count -1; j++){
            list[j] = list[j + 1];
        }
        return count - 1;
    }
}    
  return count;
}
//case f
public String displayReport(){
    String report = "";
    for(int i = 0; i < total; i++){
        if(!flags[i].equals("Disregard")){
            report = report + "Message Hash: " + messageHashes[i] + "\nRecipient: " + recipients[i] + "\nMessage: " +messages[i] + "\n\n";
        }
    }
    return report;
}
}


