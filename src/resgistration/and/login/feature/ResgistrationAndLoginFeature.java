package resgistration.and.login.feature;
import java.util.Scanner;

public class ResgistrationAndLoginFeature {
    

    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      //Username being created 
      System.out.print("Enter username: ");
      String username = input.nextLine();
      
    while(!username.contains("_") || username.length() > 5){
        System.out.println("Username is not correctly formatted;"
                + "please ensure that your username contains an underscore"
                + "and is more more than 5 characters in length");
        System.out.print("Enter username again: ");
        username = input.nextLine();
    }
      System.out.println("Username sucessfully captured");
     
      
      //Password being created 
      System.out.print("Enter password: ");
       String password = input.nextLine();
     while(password.length() < 8 || !password.matches(".*[A-Z].*") 
             || !password.matches(".*[0-9].*") || !password.matches(".*[^a-zA-Z0-9].*")){
         System.out.println("Passowrd is not correctly formatted,"
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
     while(!cellphone.matches("\\+27[0-9]{1,10}")){
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
     
     
     //User entering his username and password to login 
     System.out.println("Enter username to log back in ");
       String loginUsername = input.nextLine();
       
       System.out.println("Enter password to log back in");
       String loginPassword = input.nextLine();
   if(username.equals(loginUsername) && password.equals(loginPassword)){
       System.out.print("Welcome"  + firstname + ", " + lastname +   "it is great to see you again" );
   }
   else{
       System.out.print("Username or password incorrect, please try again");
   }
    }
    
}

