package resgistration.and.login.feature;
import java.util.Scanner;

public class ResgistrationAndLoginFeature {
    

    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      Login login = new Login();
      //Username being created 
      System.out.print("Enter username: ");
      String username = input.nextLine();
      
    while(!login.checkUserName(username)){
        System.out.println("Username is not correctly formatted; "
                + "please ensure that your username contains an underscore, "
                + "and is no more than 5 characters in length");
        System.out.print("Enter username again: ");
        username = input.nextLine();
    }
      System.out.println("Username sucessfully captured");
     
      
      //Password being created 
      System.out.print("Enter password: ");
       String password = input.nextLine();
     while(!login.checkPasswordComplexity(password)){
         System.out.println("Passowrd is not correctly formatted, "
                 + "please ensure the password contains atleast 8 characters, "
                 + "a capital letter, a number and a special character");
         System.out.print("Enter password again: ");
         password = input.nextLine();
     }
      System.out.println("Password sucessfully captured");
     
     
     //Entering South African number
     System.out.print("Enter a South African cellphone: ");
     String cellphone = input.nextLine();
     //the "\\" ensures that the phone number must start with +27 and the {1,10} allows only exactly 10 digits
     while(!login.checkPhoneNumber(cellphone)){
         System.out.println("Cellphone number is incorrectly formatted"
                 + " or  does not an international code");
         System.out.print("Enter cellphone number again: ");
         cellphone = input.nextLine();
     }
     System.out.println("cellphone number successfully added");
     
     
     // User entering his/her first and last names
     System.out.print("Enter first name: ");
     String firstname = input.nextLine();
      
     System.out.print("Enter last name: ");
     String lastname = input.nextLine();
     
     String registrationMessage = login.registerUser(username, password, cellphone, firstname, lastname);
     System.out.println(registrationMessage);
             
     //User entering his username and password to login 
     System.out.print("Enter username to log back in: ");
       String loginUsername = input.nextLine();
       
       System.out.print("Enter password to log back in: ");
       String loginPassword = input.nextLine();
    
       String loginStatusMessage = login.returnLoginStatus(loginUsername, loginPassword);
       System.out.println(loginStatusMessage);
       
       //Part 2
       if(login.loginUser(loginUsername,loginPassword)){
           System.out.println("Welcome to Qickchat.");
           
           int numberofMessages = 0;
           int messagesEntered = 0;
           int menuoption = 0;
           
           //menuoption choices
           while(menuoption != 3){
               System.out.println("option 1) Send messages");
               System.out.println("Option 2) Coming soon");
               System.out.println("option 3) Quit");
               System.out.print("Choose an option: ");
               menuoption = Integer.parseInt(input.nextLine());
               
               //Ask how many managers that the user want to enter if he/she chose option 1
              if(menuoption == 1 &&  numberofMessages == 0){
                 System.out.print("How many messages you want to enter?: ");
                 numberofMessages = Integer.parseInt(input.nextLine());
              }
              
              if(menuoption == 1 && messagesEntered < numberofMessages){
                      String messageIDMessage = login.createMessageID();
                      System.out.println(messageIDMessage);
                  //Recipient cell number
                  System.out.print("Enter recipient cellphone number: ");
                  String recipient = input.nextLine();
                  while(!login.checkPhoneNumber(recipient)){
                      System.out.println("Cellphone number is incorrectedly formatted"
                              + "or does not contain an international code.");
                      System.out.print("Enter recipient cellphone number again: ");
                      recipient = input.nextLine();
                  }
                  
                  String recipientMessage = login.checkRecipientCell(recipient);
                  System.out.println(recipientMessage);
                  
                  //Messages being entered by the user
                  System.out.print("Enter your message: ");
                  String message = input.nextLine();
                  while(message.length() > 250){
                      System.out.println("Please enter a message of less than 250 characters");
                      message = input.nextLine();
                      
                  }
                  String messageStatus = login.checkMessage(message);
                  System.out.println(messageStatus);
                  System.out.println("Message sent");
                  
                  //The message Hash
                  String messageHash = login.createMessageHash();
                 
                  //Options to do with ur message
                System.out.println(" Send Message");
                System.out.println(" Disregard Message");
                System.out.println(" Store Message to send later");
                System.out.print("Select an option: ");
                String sendOption = input.nextLine();
             
                //The outcomes for the selected options
               while(!sendOption.equals("Send Message") && !sendOption.equals("Disregard Message") && !sendOption.equals("Store Message to send later")){
                   System.out.print("Select an option again: ");
                    sendOption = input.nextLine();
               }
               login.SentMessage(sendOption);
               if(sendOption.equals("Send Message")){
                   System.out.println("Message sent successfully");
                   System.out.println(login.printMessages());
                   
                }
               else if(sendOption.equals("Disregard Message")){
                   System.out.println("Press 0 to delete the message");
               }
               else{
                   System.out.println("Message successfully stored");
               }
                  messagesEntered++;
               }
              else if(menuoption == 1){
                  System.out.println("you have already all" + numberofMessages + " messages");
              } 
              else if(menuoption == 2){
                  System.out.println("Coming soon");
              }
              }
              
           //The total number of messages sent
           System.out.println("Total number of messages sent: " +login.returnTotalMessages());
           
           }
       }
    }
    


