
package resgistration.and.login.feature;


public class Login {
      private String username;
      private String password;
      private String firstname;
      private String lastname;

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
            return "Username is incorrectly formatted";
        }
     else if(!checkPasswordComplexity(password)){
          return "Password does not meet the complexity requirements";
      }
     else if(!checkPhoneNumber(cellphone)){
          return "Cellphone number incorrectly formatted or does not contain international code ";  
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
            return "Welcome" + firstname + "," + lastname + "it is great to see you again";
        }
        else{
            return "Username or password is incorrect";
            
        }
}
}