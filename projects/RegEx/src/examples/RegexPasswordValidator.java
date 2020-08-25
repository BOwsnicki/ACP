package examples;

public class RegexPasswordValidator
{

String strRegEx = "^(?=.*[0-9]).{8,15}$";
 
String[] strPasswords = {
        "mypassword",
        "00000000",
        "AlphaRomeo4c",
        "fiatlinea2014",
        "F@rd1co",
        "F@rd1coSports",
        "Suzuki@lpha2016",
        "!vwvento2015",
        "!@#$%^&*Aa1",
        "myDream1@$$",
        "Hello World@001"
};
 
 public RegexPasswordValidator()
 {
   for(String password : strPasswords){
    
     if(password.matches( strRegEx ))
        System.out.println("valid  : " + password);
     else
        System.out.println("invalid: " + password);
   }
 }

  public static void main(String[] args)
  {
    new RegexPasswordValidator();
  }

}