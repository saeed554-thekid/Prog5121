package resgistration.and.login.feature;

import java.io.*;

public class Login {
      private String username;
      private String password;
      private String firstname;
      private String lastname;
      
      //Fields for part 2
      private String messageID;
      private int numMessageSent = '0';
      private String recipient; 
      private String messageText;
      private String MessageHash; 
      private String allMesssages = "";       

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
    return messageID.length() <=10;
}
public String checkRecipientCell(String cellphone){
    if(checkPhoneNumber(cellphone)){
        recipient = cellphone;
        return "Cellphone number sucessfully captured.";
}
    else{
    return "Cellphone number is incorrectly formatted"
            + " or does not contain an international code." ;
}    
     
 }
public String checkMessage(String text){
    if(text.length() <= 250){
        messageText = text;
        return "Message ready to send.";
    }
    else{
        return "Message exceeds 250 characters by " + (textlenght() - 250) + "; please reduce the size.";
    }     
}

public String createMessageHash(){
    String[] word =  messageText.split(" ");
    String firstWord = word[0].replaceAll(".*[^a-zA-Z0-9].*","");
    String lastWord = words[words.length - 1].replaceAll(".*[^a-zA-Z0-9].*", "");
    messageHash = (messageID.substring(0, 2) + ":" + numMessageSent + ":" + firstWord + lastWord).toUpperCase();
    return messageHash;
}

//options to do with messages
public String SentMessage(String sendOption){
    if(sendOptoin.equals("Send Message")){
        allMessages = allMessages + "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText + "\n\n";
        numMessageSent++;
        return "Message Sent.";
    }
    else if(sendOption.equals("Disgard Message")){
        return "Press 0 to delete the message.";
    }
    else{
        storeMessage();
        return"Message successfully stored.";
    }
     
}
public String printMessage(){
    return allMessages;
}
public int returnTotalMessages(){
    return numMessageSent;
}
//This stores messages in the JSON file
public void storeMessage(){
    try{
        BufferedWriter writer = new BufferedWriter(new FileWriter("storeMessage.json", true));
        writer.write("{\"messageID\": \"" + messageID + "\", \"message\":\"" + messageHash + "\", \"recipient\": \"" + recipient + "\", \"message\": \""  + messageText + "\"}" );
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
public void setNumMessageSent( int numMessageSent){
    this.numMessageSent = numMessageSent;
}
}


