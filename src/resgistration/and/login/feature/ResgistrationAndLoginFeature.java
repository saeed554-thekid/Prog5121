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
                + "and is more more than 5 characters in length");
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
     //the "\\" ensures that the phone number must start with +27 and the {9} allows only exactly 9 digits
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
     System.out.println("Enter username to log back in ");
       String loginUsername = input.nextLine();
       
       System.out.println("Enter password to log back in");
       String loginPassword = input.nextLine();
    
       String loginStatusMessage = login.returnLoginStatus(loginUsername, loginPassword);
       System.out.println(loginStatusMessage);
    }
    
}

